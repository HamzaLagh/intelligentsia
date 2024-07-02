package com.intelligentsia_backend.entity;

import jakarta.persistence.*;

public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name="module_id", nullable=false)
    Module module;
}
