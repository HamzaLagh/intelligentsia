package com.intelligentsia_backend.repository;

import com.intelligentsia_backend.entity.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RessourceRepository extends JpaRepository<Ressource,Long> {
}
