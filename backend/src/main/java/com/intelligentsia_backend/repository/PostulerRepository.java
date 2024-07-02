package com.intelligentsia_backend.repository;

import com.intelligentsia_backend.entity.JobOffer;
import com.intelligentsia_backend.entity.Postuler;
import com.intelligentsia_backend.entity.Society;
import com.intelligentsia_backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostulerRepository extends JpaRepository<Postuler,Long> {
    Postuler findByStudent_id(Long id);
    @Query("SELECT p FROM Postuler p WHERE p.jobOffer.society.id = :id")
    Iterable<Postuler> findAllBySociety_id(Long id);
}
