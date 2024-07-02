package com.intelligentsia_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class HomeWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name="course_id", nullable=false)
    Course course;

}
