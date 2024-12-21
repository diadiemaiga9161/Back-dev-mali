package com.Projet.Projet.utilisateur.Commentaires_user;

import com.Projet.Projet.Connaissances.Connaissances;
import com.Projet.Projet.ExperienceProfessionnelle.ExperienceProfessionnelle;
import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.utilisateur.User.User;
import com.Projet.Projet.utilisateur.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Object Ajouter(Commentaire commentaire) {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        // Obtenir l'utilisateur à partir de la base de données en fonction de l'username
        Optional<User> userOptional = userRepository.findByEmail(currentUsername);
        if (userOptional.isPresent()) {
            commentaire.setUser(userOptional.get());
            commentaireRepository.save(commentaire);
            return new MessageResponse("Ajouter avec succes", true);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Experience non trouvé", false));
        }
    }
    @Override
    public Commentaire AfficherParId(Long id) {
        return commentaireRepository.findById(id).get();
    }
}
