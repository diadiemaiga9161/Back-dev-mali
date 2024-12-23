package com.Projet.Projet.utilisateur.Commentaires_user;

import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.RendezVous.RendezVous;
import com.Projet.Projet.utilisateur.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:49775"}, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("api/commentaires")
public class CommentairesController {

    @Autowired
    private CommentairesService commentairesService;

    //AJOUTER UNE COMMENTAIRES
//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping("/ajouter")
//    public Object Ajouter(@RequestBody Commentaire commentaire){
//        return commentairesService.Ajouter(commentaire);
//    }
    @PostMapping("/ajouter")
    public Object Ajouter(@Valid @ModelAttribute Commentaire commentaire,
                          @RequestParam("userRecu") User user){
        return commentairesService.Ajouter(commentaire,user);// LA METHODE PERMETTANT D'AJOUTER UN Commentaire en fonction du profil choix et de l'utulisateur connecter
    }

    @GetMapping("/voir")
    public  List<Map<String, Object>> voir(){
        return commentairesService.getCommentaireByUser();
    }

    //AFFICHER LES COMMENTAIRES
    @GetMapping("/afficher")
    public List<Commentaire> Afficher(){
        return commentairesService.Afficher();
    }

    //MODIFIER UN COMMENTAIRES
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier"})
    public MessageResponse Modifier(@RequestBody Commentaire commentaire){
        commentairesService.Modifier(commentaire);
        return new MessageResponse("COMMENTAIRES Modifiee avec succes", true);
    }

    //SUPPRIMER UN COMMENTAIRES
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_commentaires}")
    public MessageResponse Supprimer(@PathVariable("id_commentaires") Long id_commentaires){
        return commentairesService.Supprimer(id_commentaires);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficherparId/{id}")
    public Commentaire AfficherParId(@PathVariable("id") Long id){
        return commentairesService.AfficherParId(id);
    }
}
