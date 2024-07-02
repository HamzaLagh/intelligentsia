package com.intelligentsia_backend.repository;

import com.intelligentsia_backend.entity.Society;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocietyRepository extends JpaRepository<Society,Long> {
    Society findByEmail(String Email);
}
