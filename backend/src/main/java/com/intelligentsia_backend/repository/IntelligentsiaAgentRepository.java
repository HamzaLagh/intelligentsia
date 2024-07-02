package com.intelligentsia_backend.repository;

import com.intelligentsia_backend.entity.IntelligentsiaAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntelligentsiaAgentRepository extends JpaRepository<IntelligentsiaAgent,Long> {}
