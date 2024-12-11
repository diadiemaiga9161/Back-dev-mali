package com.Projet.Projet.Connaissances;

import com.Projet.Projet.Connaissances.TypeConnaissance.TypeConnaissances;
import com.Projet.Projet.utilisateur.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "connaissance")
@NoArgsConstructor
@AllArgsConstructor
public class Connaissances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String  type;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "connaissances")
    List<User> user = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_typeConnaissances")
    private TypeConnaissances typeConnaissances;

//    @JsonBackReference
//    @ManyToOne
//    @JoinColumn(name = "id_utilisateur")
//    private User user;
}
