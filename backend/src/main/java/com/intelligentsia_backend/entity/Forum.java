package com.intelligentsia_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="course_id", nullable=false)
    private Course course;
    @ManyToOne
    @JoinColumn(name="student_id", nullable=false)
    private Student student;
    private Date date=new Date();
}
