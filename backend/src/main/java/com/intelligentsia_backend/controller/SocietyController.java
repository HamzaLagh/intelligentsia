package com.intelligentsia_backend.controller;

import com.intelligentsia_backend.entity.Society;
import com.intelligentsia_backend.model.SocietyModel;
import com.intelligentsia_backend.repository.SocietyRepository;
import com.intelligentsia_backend.services.service.SocietyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
public class SocietyController {
    @Autowired
    private SocietyRepository societyRepository;
    @Autowired
    private SocietyService societyService;
    @PostMapping("/registerSociety")
    public Society registerSociety(@RequestBody SocietyModel societyModel){
        Society society1=societyRepository.findByEmail(societyModel.getEmail());
        if(society1 != null)
            return society1;
        Society society=societyService.Registration(societyModel);

        return society;
    }
    @GetMapping("/recupSociety")
    @PostAuthorize("hasAuthority('SOCIETY')")
    public Society getSociety(@RequestParam("email") String email){
        return societyRepository.findByEmail(email);
    }

}
