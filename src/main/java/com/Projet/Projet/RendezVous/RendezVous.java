package com.Projet.Projet.RendezVous;

import com.Projet.Projet.utilisateur.User.User;
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
@Table(name = "rendezvous")
@NoArgsConstructor
@AllArgsConstructor
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 200)
    private String objet;

    private Date dateenvoie;

    @NotBlank
    @Size(max = 120)
    private String dateRendezvous;

    @NotBlank
    @Size(max = 120)
    private String heureRendezvous;


    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private User user;
}