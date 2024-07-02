package com.intelligentsia_backend.security_config.event;

import com.intelligentsia_backend.entity.User;
import com.intelligentsia_backend.services.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSender emailSender;
    private SimpleMailMessage message = new SimpleMailMessage();


    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //Create the Verification Token for the User with Link
        User user = event.getUser();
        String token= UUID.randomUUID().toString().substring(0,6);
        userService.saveVerificationTokenForUser(token, user);

        //Sent Mail to User

        this.message.setFrom("malidoniso1@gmail.com");
        this.message.setTo(user.getEmail());
        this.message.setSubject("Intelligentsia");
        this.message.setText("Click the link to verify your account: " + token);
        this.emailSender.send(message);

        //log.info("Click the link to verify your account: {}", token);
    }
}
