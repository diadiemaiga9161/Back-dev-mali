package com.Projet.Projet.ProjetInformatique.TypeProjet;

import com.Projet.Projet.ProjetInformatique.ProjetInformatique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeProjetRepository extends JpaRepository<TypeProjet, Long> {
    TypeProjet findByTypeprojet(String typeprojet);
}
