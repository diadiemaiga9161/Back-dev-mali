package com.Projet.Projet.ExperienceProfessionnelle;


import com.Projet.Projet.ProjetInformatique.ProjetInformatique;
import com.Projet.Projet.utilisateur.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface ExperienceProfessionnelleRepository extends JpaRepository<ExperienceProfessionnelle, Long> {

    List<ExperienceProfessionnelle> findByUser(User user);

}
