package com.Projet.Projet.utilisateur.Specialite;

import com.Projet.Projet.Message.MessageResponse;

import java.util.List;

public interface SpecialiteService {
    MessageResponse Supprimer(Long id_specialite);  // LA METHODE PERMETTANT DE SUPPRIMER UNE SPECIALITE

    Specialite Modifier(Specialite specialite);   // LA METHODE PERMETTANT DE MODIFIER UNE SPECIALITE

    List<Specialite> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES SPECIALITES

    Object Ajouter(Specialite specialite); // LA METHODE PERMETTANT D'AJOUTER UNE SPECIALITE


    Specialite AfficherParId(Long id);
}
