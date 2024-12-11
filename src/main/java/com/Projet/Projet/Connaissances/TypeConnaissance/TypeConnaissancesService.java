package com.Projet.Projet.Connaissances.TypeConnaissance;


import com.Projet.Projet.Message.MessageResponse;
import java.util.List;

public interface TypeConnaissancesService {
    MessageResponse Supprimer(Long id_typeConnaissances);  // LA METHODE PERMETTANT DE SUPPRIMER UN TYPE

    TypeConnaissances Modifier(TypeConnaissances typeConnaissances);   // LA METHODE PERMETTANT DE MODIFIER UN TYPE
    List<TypeConnaissances> Afficher();       // LA METHODE PERMETTANT D'AFFICHER LES TYPES

    Object AfficherParId(Long id);
    Object Ajouter(TypeConnaissances typeConnaissances); // LA METHODE PERMETTANT D'AJOUTER UN TYPE

}
