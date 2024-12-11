package com.Projet.Projet.RendezVous;

import com.Projet.Projet.utilisateur.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByUser(User utilisateur);
    List<RendezVous> findByUserEvoyer(User utilisateur);
    List<RendezVous> findByUserAndIsNotifiedFalse(User user);

}
