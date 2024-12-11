package com.Projet.Projet.RendezVous.TypeRdv;

import com.Projet.Projet.ProjetInformatique.TypeProjet.TypeProjet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRdvRepository extends JpaRepository<TypeRdv, Long> {
    TypeRdv findByTyperdv(String typeRdv);
}