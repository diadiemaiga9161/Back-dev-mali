package com.Projet.Projet.utilisateur.Commentaires_user;

import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.RendezVous.RendezVous;
import com.Projet.Projet.utilisateur.User.User;

import java.util.List;
import java.util.Map;

public interface CommentairesService {
    MessageResponse Supprimer(Long id_commentaires);  // LA METHODE PERMETTANT DE SUPPRIMER UNE Commentaires

    Commentaire Modifier(Commentaire commentaire);   // LA METHODE PERMETTANT DE MODIFIER UNE Commentaires

    List<Commentaire> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES COMMENTAIRES

//    Object Ajouter(Commentaire commentaire); // LA METHODE PERMETTANT D'AJOUTER UN Commentaires

    List<Map<String, Object>> getCommentaireByUser();

    List<Map<String, Object>> AfficherCommentaireParEnvoyerParUserConnecterNew();


    Object Ajouter(
            Commentaire commentaire,
            User idUser); // LA METHODE PERMETTANT D'AJOUTER UN Commentaire en fonction du profil choix et de l'utulisateur connecter


    Commentaire AfficherParId(Long id);
}
