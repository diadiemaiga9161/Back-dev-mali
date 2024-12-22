package com.Projet.Projet.utilisateur.Biographies;

import com.Projet.Projet.Message.MessageResponse;

import java.util.List;
import java.util.Map;

public interface BiographieService {

    MessageResponse Supprimer(Long id_bigraphie);  // LA METHODE PERMETTANT DE SUPPRIMER UNE Biographie

    Biographie Modifier(Biographie biographie);   // LA METHODE PERMETTANT DE MODIFIER UNE Biographie

    List<Map<String, Object>> getBiographieByUser();


    List<Biographie> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES Biographie

    Object Ajouter(Biographie biographie); // LA METHODE PERMETTANT D'AJOUTER UNE Biographie

//    Object Ajouter(
//            Biographie biographie,
//            User idUser); // LA METHODE PERMETTANT D'AJOUTER UN Commentaire en fonction du profil choix et de l'utulisateur connecter


    Biographie AfficherParId(Long id);
}
