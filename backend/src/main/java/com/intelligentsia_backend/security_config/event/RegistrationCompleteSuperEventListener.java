package com.intelligentsia_backend.security_config.event;

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
public class RegistrationCompleteSuperEventListener implements ApplicationListener<RegistrationCompleteSuperEvent> {
    @Autowired
    private JavaMailSender emailSender;
    private SimpleMailMessage message = new SimpleMailMessage();
    @Autowired
    private UserService userService;
    @Override
    public void onApplicationEvent(RegistrationCompleteSuperEvent event) {
        String token = UUID.randomUUID().toString();
       userService.saveVerificationTokenForSuper(token,event.getEmail());
        String url =
                event.getApplicationUrl()
                        + "/registrationInstructor?token="
                        + token;

        if ("Staff".equals(event.getType())){
             url =
                    event.getApplicationUrl()
                            + "/registrationStaff?token="
                            + token;
        }

        log.info("Click the link to verify your account: {}",
                url);

    }
}
