package com.Projet.Projet.ExperienceProfessionnelle;
import com.Projet.Projet.utilisateur.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "experience")
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceProfessionnelle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    private String datedebut;

    private String datefin;

    private String lieux;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private User user;

}
