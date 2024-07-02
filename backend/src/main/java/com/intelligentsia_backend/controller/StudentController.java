package com.intelligentsia_backend.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelligentsia_backend.entity.Student;
import com.intelligentsia_backend.entity.User;
import com.intelligentsia_backend.model.StudentModel;
import com.intelligentsia_backend.security_config.event.RegistrationCompleteEvent;
import com.intelligentsia_backend.services.service.StudentService;
import com.intelligentsia_backend.services.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Slf4j
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/registerStudent")
    public Student registerStudent(@RequestParam("cv") MultipartFile cv, @RequestParam("studentModelString") String studentModelString) throws IOException {
        StudentModel studentModel= new ObjectMapper().readValue(studentModelString, StudentModel.class);
        Student student1=studentService.findByEmail(studentModel.getEmail());
        log.info(studentModel.getEmail());
        if(student1 != null){
            User user1= new User();
            user1.setRole(student1.getRole());
            user1.setEmail(student1.getEmail());
            user1.setId(student1.getId());
            user1.setEnabled(student1.getEnabled());
            user1.setPassword(student1.getPassword());
            publisher.publishEvent(new RegistrationCompleteEvent(user1));
            return  student1;
        }


        Student student = studentService.registerStudent(studentModel,cv);
        User user= new User();
        user.setRole(student.getRole());
        user.setEmail(student.getEmail());
        user.setId(student.getId());
        user.setEnabled(student.getEnabled());
        user.setPassword(student.getPassword());
        publisher.publishEvent(new RegistrationCompleteEvent(user));
        return  student;
    }

    @PostMapping("/profil")
    public boolean profil(@RequestParam("id") Long id, @RequestParam("profil") MultipartFile profil) throws IOException {
        return studentService.ajoutProfil(id,profil);
    }
    @GetMapping("/getStudent")
    @PostAuthorize("hasAuthority('STUDENT')")
    public Student getStudent(@RequestParam("email") String email){
        return studentService.findByEmail(email);
    }
}
