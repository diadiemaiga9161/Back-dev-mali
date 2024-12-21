package com.Projet.Projet.utilisateur.Commentaires_user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
    Commentaire findByCommentaires(String commentaire);
}
