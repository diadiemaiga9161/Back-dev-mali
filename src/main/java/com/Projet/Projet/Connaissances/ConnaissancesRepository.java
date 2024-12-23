package com.Projet.Projet.Connaissances;

import com.Projet.Projet.utilisateur.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnaissancesRepository extends JpaRepository<Connaissances, Long> {
    List<Connaissances> findByUser(User user);


}
