package com.intelligentsia_backend.model;

import com.intelligentsia_backend.entity.JobOffer;
import com.intelligentsia_backend.entity.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostulerModel implements Serializable {

    private Long jobOfferId;
    private Long studentId;
    private boolean accept;

    private String comment ;
    @Column(name = "file_name")
    private String fileCvName;
    @Column(name = "file_path")
    private String fileCvPath;
    private Date date = new Date();
}
