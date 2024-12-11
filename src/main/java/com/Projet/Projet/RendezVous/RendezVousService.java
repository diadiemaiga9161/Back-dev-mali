package com.Projet.Projet.RendezVous;

import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.utilisateur.User.User;

import java.util.List;
import java.util.Map;

public interface RendezVousService {
    MessageResponse Supprimer(Long id_message);  // LA METHODE PERMETTANT DE SUPPRIMER UN RDV

    RendezVous Modifier(RendezVous rendezVous);   // LA METHODE PERMETTANT DE MODIFIER UN RDV

    List<RendezVous> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES RDV

    Object Ajouter(
            RendezVous rendezVous,
            Long typeRendezVousId,
            User idUser); // LA METHODE PERMETTANT D'AJOUTER UN RDV


    RendezVous AfficherParId(Long id);

    //AFFICHER LA LISTE DES RDV RECU PAR USER CONNECTE
    List<Map<String, Object>> AfficherRdvParRecuParUserConnecter();


    //AFFICHER LA LISTE DES RDV ENVOYER PAR USER CONNECTE
    List<Map<String, Object>> AfficherRdvParEnvoyerParUserConnecterNew();
}
