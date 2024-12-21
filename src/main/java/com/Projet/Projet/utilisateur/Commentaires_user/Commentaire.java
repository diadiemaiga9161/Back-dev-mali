package com.Projet.Projet.utilisateur.Commentaires_user;

import com.Projet.Projet.utilisateur.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "commentaire")
@NoArgsConstructor
@AllArgsConstructor
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(max = 500)
    private String commentaires;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private User user;
}
