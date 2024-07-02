package com.intelligentsia_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class takeCourse {
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
    @OneToMany(mappedBy = "takeCourse")
    private List<Progress> progresses = new ArrayList<>();
    private Date date=new Date();

}
