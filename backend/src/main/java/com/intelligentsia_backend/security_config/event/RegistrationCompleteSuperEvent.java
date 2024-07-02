package com.intelligentsia_backend.security_config.event;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class RegistrationCompleteSuperEvent extends ApplicationEvent {
    private final String email;
    private final String type;
    private final String applicationUrl;
    public RegistrationCompleteSuperEvent(String email, String type, String applicationUrl) {
        super(email);
        this.email=email;
        this.type = type;
        this.applicationUrl = applicationUrl;
    }

    }