package com.Projet.Projet.ProjetInformatique.TypeProjet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "typeprojet")
@NoArgsConstructor
@AllArgsConstructor
public class TypeProjet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 200)
    private String typeprojet;
}
