package com.intelligentsia_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Entity
@Data
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name="courses_id", nullable=false)
    private  Course course;
    @OneToMany(mappedBy = "module")
    private List<Ressource> ressource;
    private Date startDate;
    private Date updateDate;
}