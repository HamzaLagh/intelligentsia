package com.intelligentsia_backend.controller;

import com.intelligentsia_backend.entity.JobOffer;
import com.intelligentsia_backend.entity.Postuler;
import com.intelligentsia_backend.entity.Society;
import com.intelligentsia_backend.model.PostulerModel;
import com.intelligentsia_backend.services.service.PostulerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
public class PostulerController {
    @Autowired
    private PostulerService postulerService;
    @PostMapping("/registerPostuler")
    @PostAuthorize("hasAuthority('STUDENT')")
    public Postuler registerPostuler(@RequestBody PostulerModel postulerModel){
         return postulerService.save(postulerModel);
    }
    @GetMapping("/recupPostuler")
    @PostAuthorize("hasAuthority('STUDENT')")
    public boolean recupPostuler(@RequestParam("email") String email){
        if(postulerService.recupPostuler(email)!=null)
            return true;
        return false;
    }
    @GetMapping("/recupAllPostulerSociety")
    @PostAuthorize("hasAuthority('SOCIETY')")
    public Iterable<Postuler> recupAllPostulerSociety(@RequestParam("id") Long id){
        return postulerService.recupAllPostulerSociety(id);
    }
}
