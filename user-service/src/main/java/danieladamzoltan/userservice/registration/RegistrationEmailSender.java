package danieladamzoltan.userservice.registration;

import danieladamzoltan.userservice.persistence.models.User;
import danieladamzoltan.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationEmailSender implements ApplicationListener<RegistrationEvent> {

    private final UserService service;
    private final MessageSource messages;
    private final JavaMailSender mailSender;
    private final Environment env;

    @Autowired
    public RegistrationEmailSender(UserService service, MessageSource messages, JavaMailSender mailSender, Environment env) {
        this.service = service;
        this.messages = messages;
        this.mailSender = mailSender;
        this.env = env;
    }

    @Override
    public void onApplicationEvent(final RegistrationEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final RegistrationEvent event) {
        final User user = event.getUser();
        final String token = UUID.randomUUID().toString();
        service.createVerificationTokenForUser(user, token);

        final SimpleMailMessage email = constructEmailMessage(event, user, token);
        mailSender.send(email);
    }

    private SimpleMailMessage constructEmailMessage(final RegistrationEvent event, final User user, final String token) {
        final String toAddress = user.getEmail();
        final String subject = "Fiók regisztráció megerősítése";
        final String confirmationUrl = event.getAppUrl() + "/registrationConfirm.html?token=" + token;
        final String message = messages.getMessage("message.regSuccLink", null, "Sikeresen regisztrált. A regisztráció megerősítéséhez kattintson az alábbi linkre.", event.getLocale());
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(toAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        //maybe bad
        email.setFrom(env.getProperty("support.email"));
        return email;
    }


}