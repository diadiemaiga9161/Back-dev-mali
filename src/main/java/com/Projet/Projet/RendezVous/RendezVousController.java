package com.Projet.Projet.RendezVous;

import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.utilisateur.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:49775"}, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("api/rdv")
public class RendezVousController {
    @Autowired
    private RendezVousService rendezVousService;

    //AJOUTER UN RDV
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter")
    public Object Ajouter(@Valid @ModelAttribute RendezVous rendezVous,
                          @RequestParam("typeRendezVousId") Long typeRendezVousId,
                          @RequestParam("userRecu") User user){
        return rendezVousService.Ajouter(rendezVous,typeRendezVousId, user);
    }

    //AFFICHER LES MESSAGES
   // @PreAuthorize("hasRole('ADMIN')")qui veut dire seule l'admin peut joue sur cette fonction
    @GetMapping("/afficher")
    public List<RendezVous> Afficher(){
        return rendezVousService.Afficher();
    }

    //MODIFIER UN MESSAGE
//   @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier"})
    public MessageResponse Modifier(@RequestBody RendezVous rendezVous){
        rendezVousService.Modifier(rendezVous);
        return new MessageResponse("Rendez-vous Modifie avec succes", true);
    }

    //SUPPRIMER UN MESSAGE
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_rdv}")
    public MessageResponse Supprimer(@PathVariable("id_rdv") Long id_rdv){
        return rendezVousService.Supprimer(id_rdv);
    }

//   @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficherparId/{id}")
    public RendezVous AfficherParId(@PathVariable("id") Long id){
        return rendezVousService.AfficherParId(id);
    }

    //AFFICHER LA LISTE DES RDV RECU PAR USER CONNECTE
    @GetMapping("/get/mine")
    public List<Map<String, Object>> AfficherRdvParRecuParUserConnecter(){
        return rendezVousService.AfficherRdvParRecuParUserConnecter();
    }

    //AFFICHER LA LISTE DES RDV ENVOYER PAR USER CONNECTE
    @GetMapping("/get")
    public List<Map<String, Object>> AfficherRdvParEnvoyerParUserConnecterNew(){
        return rendezVousService.AfficherRdvParEnvoyerParUserConnecterNew();
    }
}
