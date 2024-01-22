package com.Projet.Projet.RendezVous;

import com.Projet.Projet.Message.MessageResponse;

import java.util.List;

public interface RendezVousService {
    MessageResponse Supprimer(Long id_message);  // LA METHODE PERMETTANT DE SUPPRIMER UN RDV

    RendezVous Modifier(RendezVous rendezVous);   // LA METHODE PERMETTANT DE MODIFIER UN RDV

    List<RendezVous> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES RDV

    Object Ajouter(RendezVous rendezVous); // LA METHODE PERMETTANT D'AJOUTER UN RDV


    RendezVous AfficherParId(Long id);
}
