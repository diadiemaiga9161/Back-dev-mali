package com.Projet.Projet.Connaissances;



import com.Projet.Projet.ExperienceProfessionnelle.ExperienceProfessionnelle;
import com.Projet.Projet.Message.MessageResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ConnaissancesService {

    MessageResponse Supprimer(Long id_Connaissances);  // LA METHODE PERMETTANT DE SUPPRIMER UN ExperienceProfessionnelle

    Connaissances Modifier(Connaissances connaissances);   // LA METHODE PERMETTANT DE MODIFIER UN ExperienceProfessionnelle

    List<Connaissances> Afficher();      // LA METHODE PERMETTANT D'AFFICHER LES Connaissances

    List<Map<String, Object>> getConnaissanceslByUser();


    Object Ajouter(Connaissances connaissances); // LA METHODE PERMETTANT D'AJOUTER UNE Connaissances

    Connaissances ConnaissancesId(Long id_Connaissances); // LA METHODE PERMETTANT D'AFFICHER UNE Connaissances EN FONCTION DE SON ID

    MessageResponse modifierConnaissances(
            Long id,
            String nom);
}

