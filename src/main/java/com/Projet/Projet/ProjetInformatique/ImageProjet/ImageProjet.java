package com.Projet.Projet.ProjetInformatique.ImageProjet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Entity

@Table(name = "imagesprojet")
@NoArgsConstructor
@AllArgsConstructor
public class ImageProjet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
