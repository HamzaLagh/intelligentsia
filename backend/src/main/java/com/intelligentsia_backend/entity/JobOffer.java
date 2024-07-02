package com.intelligentsia_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String titre;
    @Lob
    private String description;
    private Date dateCreate = new Date();
    private Date dateLimite;
    private int nbrPostePropose;
    private String secteurActivite;// Informatique,..
    private String fonction; //Call Centers (métiers de) , Informatique / Electronique
    private String niveauEtude;//Niveau d’étude demandé
    private String typeContrat;// CDI ou ...
    private String typeDeTravail; // En distance ou en Présentiel
    private String avantage;
    private String region;
    private String softkil;
    private String experience;
    //private String descriptionProfilRecherche ;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="society_id", nullable=false)
    private Society society;


}
