package com.Projet.Projet.ProjetInformatique;

import com.Projet.Projet.utilisateur.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetInformatiqueRepository extends JpaRepository<ProjetInformatique, Long> {

    List<ProjetInformatique> findByUser(User user);

}
