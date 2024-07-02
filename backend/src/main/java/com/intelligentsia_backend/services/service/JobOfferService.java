package com.intelligentsia_backend.services.service;

import com.intelligentsia_backend.entity.JobOffer;
import com.intelligentsia_backend.entity.Society;

import java.util.Optional;

public interface JobOfferService {
    JobOffer registerJobOffer(JobOffer jobOffer);

    void delete(Long id);

    //Iterable<JobOffer> OfferSocietyAllId(Long id);

    JobOffer update(JobOffer jobOfferParameter);
    Society getSociety(Long id);

    Iterable<JobOffer> getAllJobOfferNew();

    Optional<JobOffer> getOffer(Long id);

    JobOffer findById(Long jobOfferId);
}
