package com.intelligentsia_backend.repository;

import com.intelligentsia_backend.entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer,Long> {
    Date date = new Date();
    Iterable<JobOffer> findAllBySociety_id(Long id);
    @Query("SELECT j FROM JobOffer j WHERE j.dateLimite > :now")
    Iterable<JobOffer> findAllByDateNew(Date now);

}
