package com.intelligentsia_backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Postuler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="jobOffer_id",nullable=false)
    private JobOffer jobOffer;

    @ManyToOne
    @JoinColumn(name="student_id",nullable=false)
    private Student student;
    private Boolean accept;

    private String comment ;
    @Column(name = "file_name")
    private String fileCvName;
    @Column(name = "file_path")
    private String fileCvPath;
    private Date date = new Date();
}
