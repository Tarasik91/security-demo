package com.example.springsecuritydemo.listener;

import com.example.springsecuritydemo.event.OnRegistrationCompleteEvent;
import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.UUID;

@EnableAsync(proxyTargetClass = true)
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    Logger logger = LoggerFactory.getLogger(RegistrationListener.class);

    @Value("${mail.userName}")
    private String fromEmail;

    @Value("${mail.password}")
    private String emailPassword;

    @Autowired
    private UserService service;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Async
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        logger.debug("Email from  #" + fromEmail);
        logger.debug("Send mail to " + event.getUser().getEmail());
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                = event.getAppUrl() + "/api/registration-confirm?token=" + token;
        String message = messages.getMessage("message.regSucc", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(fromEmail);
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + confirmationUrl);
        mailSender.send(email);
    }
}
