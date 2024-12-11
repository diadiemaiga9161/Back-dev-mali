package com.Projet.Projet.ProjetInformatique;

import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.ProjetInformatique.TypeProjet.TypeProjet;
import com.Projet.Projet.utilisateur.User.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProjetInformatiqueService {


    MessageResponse Supprimer(Long id_ProjetInformatique);  // LA METHODE PERMETTANT DE SUPPRIMER UN ProjetInformatique

    ProjetInformatique Modifier(ProjetInformatique ProjetInformatique);   // LA METHODE PERMETTANT DE MODIFIER UN ProjetInformatique

    List<ProjetInformatique> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES ProjetInformatique

    List<ProjetInformatique> getProjetsByUser(User user);

    List<Map<String, Object>> getProjetInformatiquelByUser();


    Object Ajouter(String titre, String description, TypeProjet typeProjet, MultipartFile photo); // requette parme LA METHODE PERMETTANT D'AJOUTER UN ProjetInformatique

    ProjetInformatique ProjetInformatiqueparId(Long id_ProjetInformatique); // LA METHODE PERMETTANT D'AFFICHER UN ProjetInformatique EN FONCTION DE SON ID

    MessageResponse modifierProjetInformatique(
            Long id,
            String titre,
            String description,
            TypeProjet typeProjet,
            MultipartFile photo);


}

