package com.intelligentsia_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;


    private String description;

    private Date startDate;

    private Date endDate;

    private Date updateDate;

    @ManyToOne
    @JoinColumn(name="instructor_id", nullable=false)
    private Instructor instructor;
    private String themes;


    private String level;


    private String language;


    private String state;

    private Integer duration;

    @OneToMany(mappedBy="course")
    private List<Module> module;


    private Boolean isPaid;

    private Double cost;


    /*
    private List<Comment> comment;
   @OneToMany(mappedBy = "course")
     *private List<Evaluation> evaluations;
     *@OneToMany(mappedBy = "course")
     *private List<Homework> homeworks;
<<<<<<< HEAD
=======
     *private List<Progress> progress;
>>>>>>> master
     * private List<Message> message;
     * en privé en sur le groupe
    */
}
