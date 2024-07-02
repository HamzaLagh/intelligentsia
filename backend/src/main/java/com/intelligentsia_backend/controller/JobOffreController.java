package com.intelligentsia_backend.controller;

import com.intelligentsia_backend.entity.JobOffer;
import com.intelligentsia_backend.entity.Society;
import com.intelligentsia_backend.services.service.JobOfferService;
import com.intelligentsia_backend.services.service.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
public class JobOffreController {
    @Autowired
    private JobOfferService jobOfferService;
    @Autowired
    private SocietyService societyService;

    @PostMapping("/registerOffer")
    @PostAuthorize("hasAuthority('SOCIETY')")
    public JobOffer registerOffer(@RequestBody JobOffer jobOffer){
        Society society=societyService.getByEmail(jobOffer.getSociety().getEmail());
        jobOffer.setSociety(society);
        return jobOfferService.registerJobOffer(jobOffer);
    }
    @DeleteMapping("/deleteOffer")
    public void  delete(@RequestParam("id") Long id){
        jobOfferService.delete(id);
    }

    @GetMapping("/getOffer")
    public JobOffer  getOffer(@RequestParam("id") Long id){
       return jobOfferService.getOffer(id).get();
    }

    /*@GetMapping("/OfferSocietyId/{id}")
    public Iterable<JobOffer> OfferSocietyId(@PathVariable("id")  Long id){
        return jobOfferService.OfferSocietyAllId(id);
    }*/
    @PutMapping("/OfferUpdate")
    public JobOffer PutOffer(@RequestBody JobOffer jobOfferParameter){
        return jobOfferService.update(jobOfferParameter);
    }

    @GetMapping("/AllJobOfferNew")
    public Iterable<JobOffer> getAllJobOfferNew(){
        return jobOfferService.getAllJobOfferNew();
    }

    @GetMapping("/societyOffer")
    public Society getSociety(@RequestParam("id")  Long id){
        return jobOfferService.getSociety(id);
    }
}
