package com.Projet.Projet.utilisateur.Biographies;

import com.Projet.Projet.utilisateur.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BiographieRepository extends JpaRepository<Biographie, Long> {
    List<Biographie> findByUser(User user);

}