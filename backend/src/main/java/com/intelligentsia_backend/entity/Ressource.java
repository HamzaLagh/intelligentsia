package com.intelligentsia_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Ressource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name="module_id", nullable=false)
    private Module module;
    private String [] NameRessource;
    private String [] PathRessoure;
    private Date startDate;
    private Date updateDate;
}
