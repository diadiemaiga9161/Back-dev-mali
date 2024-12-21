package com.Projet.Projet.utilisateur.Commentaires_user;

import com.Projet.Projet.Message.MessageResponse;

import java.util.List;

public interface CommentairesService {
    MessageResponse Supprimer(Long id_commentaires);  // LA METHODE PERMETTANT DE SUPPRIMER UNE Commentaires

    Commentaire Modifier(Commentaire commentaire);   // LA METHODE PERMETTANT DE MODIFIER UNE Commentaires

    List<Commentaire> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES COMMENTAIRES

    Object Ajouter(Commentaire commentaire); // LA METHODE PERMETTANT D'AJOUTER UNE Commentaires

    Commentaire AfficherParId(Long id);
}
