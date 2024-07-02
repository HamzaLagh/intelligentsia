package com.intelligentsia_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="take_course_id", nullable=false)
    private takeCourse takeCourse;
    private Long sender;
    private Long receiver;
    private String content;
    private LocalDateTime timestamp;
    private boolean isRead;
    private boolean isViewed; // champ pour représenter si le message a été vu ou


}
