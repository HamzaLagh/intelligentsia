package com.intelligentsia_backend.entity;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Instructor extends User{
    private String firstname;
    private String name;
    private String address;
    private String raison_sociale;
    private  String tel;
    private  String specialization;
    private String experience;
    @Column(name = "file_name")
    private String profilName;
    @Column(name = "file_path")
    private String profilPath;
    @OneToMany(mappedBy="instructor")
    private List<Course> course;
}
