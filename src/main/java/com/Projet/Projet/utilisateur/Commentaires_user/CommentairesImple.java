package com.Projet.Projet.utilisateur.Commentaires_user;


import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.utilisateur.User.User;
import com.Projet.Projet.utilisateur.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentairesImple implements CommentairesService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CommentaireRepository commentaireRepository;

    @Override
    public MessageResponse Supprimer(Long id_commentaire) {
        commentaireRepository.deleteById(id_commentaire);
        return new MessageResponse("Commentaire Supprimee avec succes", true);
    }


    @Override
    public Commentaire Modifier(Commentaire commentaire) {
        return commentaireRepository.findById(commentaire.getId())
                .map(p->{
                    p.setCommentaires(commentaire.getCommentaires());
                    return commentaireRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Commentaire non trouvé !"));
    }

    @Override
    public List<Commentaire> Afficher() {
        return commentaireRepository.findAll();
    }

    @Override
    public  List<Map<String, Object>> getCommentaireByUser() {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        // Obtenir l'utilisateur à partir de la base de données en fonction de l'username
        Optional<User> userOptional = userRepository.findByEmail(currentUsername);
        // Récupérer les expériences professionnelles de l'utilisateur
        List<Commentaire> commentaires = commentaireRepository.findByUser(userOptional.get());
        List<Map<String, Object>> result = new ArrayList<>();

        for (Commentaire commentaire : commentaires) {
            Map<String, Object> commentaireMap = new HashMap<>();
            commentaireMap.put("id", commentaire.getId());
            commentaireMap.put("commentaire", commentaire.getCommentaires());

            result.add(commentaireMap);
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> AfficherCommentaireParEnvoyerParUserConnecterNew() {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Optional<User> userOptional = userRepository.findByEmail(currentUsername);
        // Vérifier si l'utilisateur existe
        if (userOptional.isPresent()) {
            User utilisateurConnecte = userOptional.get();
            List<Commentaire> commentairesUtilisateur = commentaireRepository.findByUserEvoyer(utilisateurConnecte);
            // Initialiser une liste pour stocker les détails des commentaires
            List<Map<String, Object>> commentairesDetailsList = new ArrayList<>();
            // Parcourir chaque commentaires dans la liste
            for (Commentaire commentaire : commentairesUtilisateur) {
                // Créer une nouvelle map pour stocker les détails du commentaire
                Map<String, Object> commentairDetails = new HashMap<>();
                // Ajouter les détails du rdv dans la map
                commentairDetails.put("id", commentaire.getId());
                commentairDetails.put("commentaire", commentaire.getCommentaires());
                commentairDetails.put("user", commentaire.getUser());
                // Ajouter les détails du commentaire à la liste
               commentairesDetailsList.add(commentairDetails);
            }
            // Retourner la liste des détails des commentaire
            return commentairesDetailsList;
        }else {
            // Gérer le cas où l'utilisateur n'est pas trouvé
            // Vous pouvez lancer une exception, retourner une liste vide ou prendre d'autres mesures selon vos besoins
            throw new RuntimeException("Utilisateur non trouvé");
        }
    }

    @Override
    public Object Ajouter(Commentaire commentaire, User idUser) {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
//        System.out.println(currentUsername);
        // Obtenir l'utilisateur à partir de la base de données en fonction de l'username
        Optional<User> userOptional = userRepository.findByEmail(currentUsername);
        if (userOptional.isPresent()) {
            commentaire.setUserEvoyer(userOptional.get());
            commentaire.setUser(idUser);
            // Récupérer le type correspondant
            commentaireRepository.save(commentaire);
            return new MessageResponse("Commentaire ajoute avec succes", true);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Utilisateur non trouvé", false));
        } // LA METHODE PERMETTANT D'AJOUTER UN Commentaire en fonction du profil choix et de l'utulisateur connecter
    }


    @Override
    public Commentaire AfficherParId(Long id) {
        return commentaireRepository.findById(id).get();
    }
}
