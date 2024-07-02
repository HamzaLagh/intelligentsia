package com.intelligentsia_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class IntelligentsiaAgent extends User{
    private String nameAgent;
}
