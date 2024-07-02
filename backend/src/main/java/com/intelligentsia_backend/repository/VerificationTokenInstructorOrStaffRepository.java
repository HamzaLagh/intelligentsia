package com.intelligentsia_backend.repository;

import com.intelligentsia_backend.entity.VerificationTokenInstructorOrStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenInstructorOrStaffRepository extends JpaRepository<VerificationTokenInstructorOrStaff,Long> {
    VerificationTokenInstructorOrStaff findByToken(String token);
}
