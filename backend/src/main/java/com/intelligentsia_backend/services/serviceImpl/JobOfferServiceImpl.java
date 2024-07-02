package com.intelligentsia_backend.services.serviceImpl;

import com.intelligentsia_backend.entity.JobOffer;
import com.intelligentsia_backend.entity.Society;
import com.intelligentsia_backend.repository.JobOfferRepository;
import com.intelligentsia_backend.services.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JobOfferServiceImpl implements JobOfferService {
    @Autowired
    private JobOfferRepository jobOfferRepository;
    @Override
    public JobOffer registerJobOffer(JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }

    @Override
    public void delete(Long id) {
        jobOfferRepository.deleteById(id);
    }

   /* @Override
    public Iterable<JobOffer> OfferSocietyAllId(Long id) {
        return jobOfferRepository.findAllBySociety_id(id);
    }*/

    @Override
    public JobOffer update(JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }

    @Override
    public Society getSociety(Long id) {
       Optional<JobOffer> jobOffer=jobOfferRepository.findById(id);
        if(jobOffer.isPresent())
            return jobOffer.get().getSociety();
        return null;
    }

    @Override
    public Iterable<JobOffer> getAllJobOfferNew() {
        Date date = new Date();
        return jobOfferRepository.findAllByDateNew(date);
    }

    @Override
    public Optional<JobOffer> getOffer(Long id) {
        return jobOfferRepository.findById(id);
    }

    @Override
    public JobOffer findById(Long jobOfferId) {
        return jobOfferRepository.findById(jobOfferId).get();
    }

}
