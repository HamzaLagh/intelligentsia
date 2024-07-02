package com.intelligentsia_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Staff extends User{

    private String matricule;
}
