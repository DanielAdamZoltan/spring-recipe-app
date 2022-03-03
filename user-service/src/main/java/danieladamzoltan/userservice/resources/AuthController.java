package danieladamzoltan.userservice.resources;

import danieladamzoltan.userservice.exception.InvalidOldPasswordException;
import danieladamzoltan.userservice.exception.UserAlreadyExistException;
import danieladamzoltan.userservice.persistence.models.User;
import danieladamzoltan.userservice.persistence.models.VerificationToken;
import danieladamzoltan.userservice.persistence.models.dto.LoginRequest;
import danieladamzoltan.userservice.persistence.models.dto.PasswordDto;
import danieladamzoltan.userservice.persistence.models.dto.UserDto;
import danieladamzoltan.userservice.persistence.models.dto.MessageResponse;
import danieladamzoltan.userservice.registration.RegistrationEvent;
import danieladamzoltan.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/authentication")
public class AuthController {



    private final ApplicationEventPublisher applicationEventPublisher;
    private final UserService userService;
    private final JavaMailSender javaMailSender;
    private final Environment environment;
    private final MessageSource messageSource;

    @Autowired
    public AuthController(ApplicationEventPublisher applicationEventPublisher, UserService userService, JavaMailSender javaMailSender, Environment environment, MessageSource messageSource) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.userService = userService;
        this.javaMailSender = javaMailSender;
        this.environment = environment;
        this.messageSource = messageSource;
    }

//    Authenticate the User
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        userService.authenticateUser(loginRequest);
//        messageSource.getMessage("message.successfullyLoggedIn", null, request.getLocale()),
        System.out.println(messageSource.getMessage("message.successfullyLoggedIn", null, null));
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Register the User
    @PostMapping("admin/register")
//  @RequestBody
    public ResponseEntity<?> registerUserAdmin(@Valid final UserDto userDto,
                                            final HttpServletRequest request) {
        try {
            User user = userService.registerNewUser(userDto);
            applicationEventPublisher.publishEvent(new RegistrationEvent(user,
                    request.getLocale(), getAppUrl(request)));
            System.out.println(messageSource.getMessage("message.registration", null, request.getLocale()));
            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch (UserAlreadyExistException userAlreadyExistException){
            System.out.println(messageSource.getMessage("message.emailAlreadyInUse", null, request.getLocale()) + "Error: " + userAlreadyExistException);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody userDto userDto,
//                                          HttpServletRequest request) {
//        try {
//            User user = userService.registerNewUser(userDto);
//            String appUrl = request.getContextPath();
//            applicationEventPublisher.publishEvent(new RegistrationEvent(user,
//                    request.getLocale(), appUrl));
//            return ResponseEntity.ok(new MessageResponse("We've sent you a link to sign up via email."));
//        }catch (Exception exception ){
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
//        }
//    }

    // User activation - verification
    @GetMapping("/user/resendRegistrationToken")
    public ResponseEntity<?> resendRegistrationToken(final HttpServletRequest request, @RequestParam("token") final String existingToken) {
        final VerificationToken verificationToken = userService.generateNewVerificationToken(existingToken);
        final User user = userService.getUser(verificationToken.getToken());
        javaMailSender.send(constructResendVerificationTokenEmail(getAppUrl(request), request.getLocale(), verificationToken, user));
        System.out.println(messageSource.getMessage("message.resendToken", null, request.getLocale()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Reset password
    @PostMapping("/user/resetPassword")
    public ResponseEntity<?> resetPassword(final HttpServletRequest request, @RequestParam("email") final String userEmail) {
        final User user = userService.findUserByEmail(userEmail);
        if (user != null) {
            final String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            javaMailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
        }
        System.out.println(messageSource.getMessage("message.resetPasswordEmail", null, request.getLocale()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Save password
    @PostMapping("/user/savePassword")
    public ResponseEntity<?> savePassword(final Locale locale, @Valid PasswordDto passwordDto) {

        final String result = userService.validatePasswordResetToken(passwordDto.getToken());

        if(result != null) {
            System.out.println(messageSource.getMessage("auth.message." + result, null, locale));
            return new ResponseEntity<>(HttpStatus.OK);
        }

        Optional<User> user = userService.getUserByPasswordResetToken(passwordDto.getToken());
        if(user.isPresent()) {
            userService.changeUserPassword(user.get(), passwordDto.getNewPassword());
            System.out.println(messageSource.getMessage("message.resetPasswordSuc", null, locale));
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(messageSource.getMessage("auth.message.invalid", null, locale), HttpStatus.BAD_REQUEST);
        }
    }

    // Change user password
    @PostMapping("/user/updatePassword")
    public ResponseEntity<?> changeUserPassword(final Locale locale, @Valid PasswordDto passwordDto) {
        final User user = userService.findUserByEmail(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
        if (!userService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
            throw new InvalidOldPasswordException(messageSource.getMessage("message.invalidOldPassword", null, locale));
        }
        userService.changeUserPassword(user, passwordDto.getNewPassword());
        System.out.println(messageSource.getMessage("message.updatePasswordSuccess", null, locale));
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Verificate the Registration token
    @GetMapping("/registration-confirm")
    public ResponseEntity<?> confirmRegistration(@RequestParam("token") String token) {
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            //maybe redirect
            System.out.println(messageSource.getMessage("auth.message.invalidToken", null, null));
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {

            //maybe redirect
            System.out.println(messageSource.getMessage("auth.message.expired", null, null));
            return new ResponseEntity<>(HttpStatus.GONE);
        }
        else {
            user.setEnabled(true);
            userService.saveRegisteredUser(user);
            System.out.println(messageSource.getMessage("message.regSuccessConfirmed", null, null));
            return new ResponseEntity<>(HttpStatus.OK);

        }
    }


//    non api


    private SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale, final VerificationToken newToken, final User user) {
        final String confirmationUrl = contextPath + "/registrationConfirm.html?token=" + newToken.getToken();
        final String message = messageSource.getMessage("message.resendToken", null, locale);
        return constructEmail("Resend Registration Token", message + " \r\n" + confirmationUrl, user);
    }

    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user) {
        final String url = contextPath + "/user/changePassword?token=" + token;
        final String message = messageSource.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, User user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(environment.getProperty("support.email"));
        return email;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}
