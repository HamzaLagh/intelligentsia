package com.intelligentsia_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Student extends User{

    private String firstname;
    private String name;
    private String address;
    private  String tel;
    private String niveauEtude;
    private String experience;
    private String typeDeFormation;
    private String sexe;
    private String autre;
    @Column(name = "file_name")
    private String fileCvName;
    @Column(name = "file_path")
    private String fileCvPath;
    @Column(name = "file_profil")
    private String fileProfilName;
    @Column(name = "file_profil_path")
    private String fileProfilPath;

}
