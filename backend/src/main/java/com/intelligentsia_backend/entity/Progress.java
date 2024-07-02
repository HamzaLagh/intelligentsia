package com.intelligentsia_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="take_course_id", nullable=false)
    private takeCourse takeCourse;


    private Long idModule;

    @ManyToOne
    @JoinColumn(name="module_id", nullable=false)
    private Module module;

    private Integer progressPercentage;
    private Boolean isCompleted;
    private Date lastUpdated;
}
