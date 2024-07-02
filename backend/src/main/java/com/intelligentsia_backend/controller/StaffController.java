package com.intelligentsia_backend.controller;

import com.intelligentsia_backend.security_config.event.RegistrationCompleteSuperEvent;
import com.intelligentsia_backend.services.service.UserService;
import com.intelligentsia_backend.services.service.StaffService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher publisher;
    @GetMapping("/aRegistrationStaff")
    public String aRegistrationStaff(@RequestParam("email") String email, @RequestParam("type")String type, final HttpServletRequest request){
        publisher.publishEvent(new RegistrationCompleteSuperEvent(email,type,applicationUrl(request)));
        return "false";
    }
    @GetMapping("/registrationStaff")
    public String registrationStaff(@RequestParam("token") String token){
       String verify= userService.validateVerificationTokenInstructorOrStaff(token);
       if (verify=="valid")
           return "StaffAUthen";
       return "Erreur";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
