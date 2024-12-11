package com.Projet.Projet.ProjetInformatique;

import com.Projet.Projet.ProjetInformatique.TypeProjet.TypeProjet;
import com.Projet.Projet.utilisateur.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "projetinformatique")
@NoArgsConstructor
@AllArgsConstructor
public class ProjetInformatique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date datecreation;

    private String titre;

    @NotBlank
    @Size(min = 10, max = 650000000)
    @Column(length = 650000000)
    private String description;

    private String photo;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_typeprojet")
    private TypeProjet typeProjet;


}
