package danieladamzoltan.userservice.resources;

import danieladamzoltan.userservice.exception.InvalidOldPasswordException;
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

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        userService.authenticateUser(loginRequest);
        return ResponseEntity.ok(new MessageResponse("You logged in successfully!"));
    }

    // Registration
//    @PostMapping("/registration")
//    public ResponseEntity<?> registerUserAccount(@Valid final userDto userDto, final HttpServletRequest request) {
//
//
//        final User user = userService.registerNewUser(userDto);
//        userService.addUserLocation(registered, getClientIP(request));
//        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
//        return new GenericResponse("success");
//    }

    @PostMapping("admin/register")
    public ResponseEntity<?> registerUserAdmin(@Valid @RequestBody UserDto userDto,
                                          HttpServletRequest request) {
        try {
            User user = userService.registerNewUser(userDto);
            String appUrl = request.getContextPath();
            applicationEventPublisher.publishEvent(new RegistrationEvent(user,
                    request.getLocale(), appUrl));
            return ResponseEntity.ok(new MessageResponse("We've sent you a link to sign up via email."));
        }catch (Exception exception ){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
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
        return new GenericResponse(messageSource.getMessage("message.resendToken", null, request.getLocale()));
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
        return new GenericResponse(messageSource.getMessage("message.resetPasswordEmail", null, request.getLocale()));
    }

    // Save password
    @PostMapping("/user/savePassword")
    public ResponseEntity<?> savePassword(final Locale locale, @Valid PasswordDto passwordDto) {

        final String result = userService.validatePasswordResetToken(passwordDto.getToken());

        if(result != null) {
            return new GenericResponse(messageSource.getMessage("auth.message." + result, null, locale));
        }

        Optional<User> user = userService.getUserByPasswordResetToken(passwordDto.getToken());
        if(user.isPresent()) {
            userService.changeUserPassword(user.get(), passwordDto.getNewPassword());

            return new GenericResponse(messageSource.getMessage("message.resetPasswordSuc", null, locale));
        } else {
            return new GenericResponse(messageSource.getMessage("auth.message.invalid", null, locale));
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
        return new GenericResponse(messageSource.getMessage("message.updatePasswordSuc", null, locale));
    }

    @GetMapping("/registration-confirm")
    public ResponseEntity<Void> confirmRegistration(@RequestParam("token") String token) {
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            //maybe redirect
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid Token!"));
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {

            //maybe redirect
            return ResponseEntity.badRequest().body(new MessageResponse("Error: The token is expired!"));
        }
        else {
            user.setEnabled(true);
            userService.saveRegisteredUser(user);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!")) && new ResponseEntity<>();

        }
    }


//    @GetMapping("/registration-confirm")
//    public String confirmRegistration(@RequestParam("token") String token) {
//        VerificationToken verificationToken = userService.getVerificationToken(token);
//        if (verificationToken == null) {
//            //maybe redirect
//            return "Error: Invalid Token!";
//        }
//
//        User user = verificationToken.getUser();
//        Calendar cal = Calendar.getInstance();
//        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//
//            //maybe redirect
//            return "Error: The token is expired!";
//        }
//        else {
//            user.setEnabled(true);
//            userService.saveRegisteredUser(user);
//            return ResponseEntity.ok(new MessageResponse("User registered successfully!")) && new ResponseEntity<>();
//
//        }
//    }

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
