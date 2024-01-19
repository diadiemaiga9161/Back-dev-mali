package com.Projet.Projet.ProjetInformatique.TypeProjet;

import com.Projet.Projet.Message.MessageResponse;

import java.util.List;

public interface TypeProjetService {
    
    MessageResponse Supprimer(Long id_typeProjet);  // LA METHODE PERMETTANT DE SUPPRIMER UN TYPE

    TypeProjet Modifier(TypeProjet typeProjet);   // LA METHODE PERMETTANT DE MODIFIER UN TYPE

    List<TypeProjet> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES TYPES

    TypeProjet AfficherParId(Long id);
    Object Ajouter(TypeProjet typeProjet); // LA METHODE PERMETTANT D'AJOUTER UN TYPE
}
