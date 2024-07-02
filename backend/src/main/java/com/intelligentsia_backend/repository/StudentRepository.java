package com.intelligentsia_backend.repository;

import com.intelligentsia_backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByEmail(String Email);
}
