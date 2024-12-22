package com.Projet.Projet.utilisateur.Biographies;

import com.Projet.Projet.utilisateur.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "biographie")
@NoArgsConstructor
@AllArgsConstructor
public class Biographie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(max = 500)
    private String biographie;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private User user;

}
