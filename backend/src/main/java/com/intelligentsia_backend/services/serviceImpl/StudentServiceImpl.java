package com.intelligentsia_backend.services.serviceImpl;

import com.intelligentsia_backend.entity.Role;
import com.intelligentsia_backend.entity.Student;
import com.intelligentsia_backend.model.StudentModel;
import com.intelligentsia_backend.repository.RoleRepository;
import com.intelligentsia_backend.repository.StudentRepository;
import com.intelligentsia_backend.services.service.StudentService;
import com.intelligentsia_backend.services.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;
    @Value("${file.upload-dir}")
    private String uploadDir;
    @Value("${file.upload-dir1}")
    private String uploadDir1;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Student registerStudent(StudentModel studentModel,MultipartFile cv) throws IOException {
        Role role= new Role();
        role.setName("STUDENT");
        role=roleRepository.save(role);
        Student student= new Student();
        student.setTel(studentModel.getTel());
        student.setAddress(studentModel.getAddress());
        student.setName(studentModel.getName());
        student.setFirstname(studentModel.getFirstname());
        student.setEmail(studentModel.getEmail());
        student.setSexe(studentModel.getSexe());
        student.setExperience(studentModel.getExperience());
        student.setNiveauEtude(studentModel.getNiveauEtude());
        student.setAutre(studentModel.getAutre());
        student.setTypeDeFormation(studentModel.getTypeDeFormation());
        student.getRole().add(role);
        String fileName = cv.getOriginalFilename();
        String filePath = Paths.get(uploadDir, fileName).toString();
        File dest = new File(filePath);
        cv.transferTo(dest);
        student.setFileCvName(fileName);
        student.setFileCvPath(filePath);
        student.setPassword(passwordEncoder.encode(studentModel.getPassword()));
        student=studentRepository.save(student);
        return student;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public boolean ajoutProfil(Long id,MultipartFile profil) throws IOException {
        Optional<Student> student=studentRepository.findById(id);
        if(student.isPresent()){
            Student student1=student.get();
            String fileName = profil.getOriginalFilename();
            String filePath = Paths.get(uploadDir1, fileName).toString();
            File dest = new File(filePath);
            profil.transferTo(dest);
            student1.setFileProfilName(fileName);
            student1.setFileProfilPath(filePath);
            return  true;
        }
        return false;
    }

    @Override
    public Student findById(Long studentId) {
        return studentRepository.findById(studentId).get();
    }
}
