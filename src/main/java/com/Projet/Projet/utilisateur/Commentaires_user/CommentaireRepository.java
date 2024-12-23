package com.Projet.Projet.utilisateur.Commentaires_user;


import com.Projet.Projet.utilisateur.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
    Commentaire findByCommentaires(String commentaire);
    List<Commentaire> findByUserEvoyer(User utilisateur);
    List<Commentaire> findByUser(User user);

}
