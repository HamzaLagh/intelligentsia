package com.intelligentsia_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Society extends User{
    private String name;
    private String siren;
    private String siret;
    private  String specialization;
    private String address;
    private  String tel;
    // private String description
    //@OneToMany(mappedBy="society", cascade = CascadeType.REMOVE)
    @OneToMany(mappedBy="society")
    private List<JobOffer> jobOffer;
    @Column(name = "file_name")
    private String fileLogoName;
    @Column(name = "file_path")
    private String fileLogoPath;

}
