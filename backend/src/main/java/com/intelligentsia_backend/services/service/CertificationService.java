package com.intelligentsia_backend.services.service;

import com.intelligentsia_backend.entity.Certification;

import java.util.Optional;

public interface CertificationService {
    public Optional<Certification> findById(Long id);
}
