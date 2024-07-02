package com.intelligentsia_backend.entity;

import jakarta.persistence.*;
@Entity
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name="course_id", nullable=false)
    Course course;
}
