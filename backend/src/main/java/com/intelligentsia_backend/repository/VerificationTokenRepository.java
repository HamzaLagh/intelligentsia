package com.intelligentsia_backend.repository;

import com.intelligentsia_backend.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByToken(String token);
    VerificationToken findByUser_Id(Long id);
}
