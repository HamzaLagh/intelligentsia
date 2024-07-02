package com.intelligentsia_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentModel implements Serializable {
    private String email ;

    private String password;

    private Boolean enable;

    private String role;
    private String firstname;
    private String name;
    private String address;
    private  String tel;
    private String niveauEtude;
    private String experience;
    private String typeDeFormation;
    private String sexe;
    private String autre;

}
