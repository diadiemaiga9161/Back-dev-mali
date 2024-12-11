package com.Projet.Projet.ExperienceProfessionnelle;

import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.utilisateur.User.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ExperienceProfessionnelleService {


    MessageResponse Supprimer(Long id_ExperienceProfessionnelle);  // LA METHODE PERMETTANT DE SUPPRIMER UN ExperienceProfessionnelle

    ExperienceProfessionnelle Modifier(ExperienceProfessionnelle experienceProfessionnelle);   // LA METHODE PERMETTANT DE MODIFIER UN ExperienceProfessionnelle

    List<Map<String, Object>> getExperienceProfessionnelleByUser();

    List<ExperienceProfessionnelle> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES ExperienceProfessionnelle

    Object Ajouter(ExperienceProfessionnelle experienceProfessionnelle); // LA METHODE PERMETTANT D'AJOUTER UN ExperienceProfessionnelle

    ExperienceProfessionnelle ExperienceProfessionnelleId(Long id_ExperienceProfessionnelle); // LA METHODE PERMETTANT D'AFFICHER UN ExperienceProfessionnelle EN FONCTION DE SON ID

    MessageResponse modifierExperienceProfessionnelle(
            Long id,
            String titre,
            String  datedebut,
            String  datefin,
            String  lieux);

}
