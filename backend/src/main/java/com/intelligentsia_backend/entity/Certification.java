package com.intelligentsia_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private boolean type;
    @ManyToOne
    @JoinColumn(name="course_id", nullable=false)
    private Course course;
    @ManyToOne
    @JoinColumn(name="student_id", nullable=false)
    private Student student;
    private Date date=new Date();
    public Certification(Course course,Student student, Date date){
        this.course=course;
        this.date=date;
        this.student=student;
    }

}
