package danieladamzoltan.userservice.resources;

import danieladamzoltan.userservice.persistence.models.User;
import danieladamzoltan.userservice.persistence.models.VerificationToken;
import danieladamzoltan.userservice.persistence.models.request.LoginRequest;
import danieladamzoltan.userservice.persistence.models.request.RegisterRequest;
import danieladamzoltan.userservice.persistence.models.response.MessageResponse;
import danieladamzoltan.userservice.registration.RegistrationEvent;
import danieladamzoltan.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/authentication")
public class AuthController {



    private final ApplicationEventPublisher applicationEventPublisher;
    private final UserService userService;

    @Autowired
    public AuthController(ApplicationEventPublisher applicationEventPublisher, UserService userService) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        userService.authenticateUser(loginRequest);
        return ResponseEntity.ok(new MessageResponse("You logged in successfully!"));
    }

    // Registration
//    @PostMapping("/registration")
//    public ResponseEntity<?> registerUserAccount(@Valid final RegisterRequest registerRequest, final HttpServletRequest request) {
//
//
//        final User user = userService.registerNewUser(registerRequest);
//        userService.addUserLocation(registered, getClientIP(request));
//        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
//        return new GenericResponse("success");
//    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest, HttpServletRequest request) {
        try {
            User user = userService.registerNewUser(registerRequest);
            String appUrl = request.getContextPath();
            applicationEventPublisher.publishEvent(new RegistrationEvent(user,
                    request.getLocale(), appUrl));
            return ResponseEntity.ok(new MessageResponse("We've sent you a link to sign up via email."));
        }catch (Exception exception ){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }


    }

    @GetMapping("/registration-confirm")
    public ResponseEntity<?> confirmRegistration(@RequestParam("token") String token) {
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
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        }
    }


}
