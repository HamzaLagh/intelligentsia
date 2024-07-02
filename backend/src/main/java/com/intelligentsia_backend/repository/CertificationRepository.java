package com.intelligentsia_backend.repository;

import com.intelligentsia_backend.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification,Long> {
}
