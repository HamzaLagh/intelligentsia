package com.intelligentsia_backend.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SocietyModel implements Serializable {
    private String email ;
    private String password;
    private String role;
    private String name;
    private String siren;
    private String siret;
    private  String specialization;
    private String address;
    private  String tel;

}
