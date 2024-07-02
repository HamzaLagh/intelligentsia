package com.intelligentsia_backend.services.service;

import com.intelligentsia_backend.entity.Student;
import com.intelligentsia_backend.model.StudentModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    Student registerStudent(StudentModel studentModel, MultipartFile cv) throws IOException;

    List<Student> getAllStudent();

    Student findByEmail(String email);

    boolean ajoutProfil(Long id,MultipartFile profil) throws IOException;

    Student findById(Long studentId);
}
