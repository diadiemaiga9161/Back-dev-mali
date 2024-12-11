package com.Projet.Projet.RendezVous.TypeRdv;

import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.ProjetInformatique.TypeProjet.TypeProjet;

import java.util.List;

public interface TypeRdvService {
    MessageResponse Supprimer(Long id_typerdv);  // LA METHODE PERMETTANT DE SUPPRIMER UN TYPE

    TypeRdv Modifier(TypeRdv typeRdv);   // LA METHODE PERMETTANT DE MODIFIER UN TYPE

    List<TypeRdv> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES TYPES

    TypeRdv AfficherParId(Long id);
    Object Ajouter(TypeRdv typeRdv); // LA METHODE PERMETTANT D'AJOUTER UN TYPE

}
