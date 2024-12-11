package com.Projet.Projet.utilisateur.Specialite;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {
    Specialite findBySpecialite(String specialite);
}
