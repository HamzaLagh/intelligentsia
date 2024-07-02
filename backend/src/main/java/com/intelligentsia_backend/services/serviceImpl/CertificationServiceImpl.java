package com.intelligentsia_backend.services.serviceImpl;

import com.intelligentsia_backend.repository.CertificationRepository;
import com.intelligentsia_backend.entity.Certification;
import com.intelligentsia_backend.services.service.CertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CertificationServiceImpl implements CertificationService {
    @Autowired
    private CertificationRepository certificationRepository;
    public Optional<Certification> findById(Long id) {
        return certificationRepository.findById(id);
    }
}
