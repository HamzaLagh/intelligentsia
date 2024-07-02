package com.intelligentsia_backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Data @NoArgsConstructor  @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String email ;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(length=60)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> role=new ArrayList<>();
    private Boolean enabled = false;
    private Date date_create = new Date();
}
