package com.Projet.Projet.ExperienceProfessionnelle;


import com.Projet.Projet.utilisateur.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceProfessionnelleRepository extends JpaRepository<ExperienceProfessionnelle, Long> {

    List<ExperienceProfessionnelle> findByUser(User user);

}
