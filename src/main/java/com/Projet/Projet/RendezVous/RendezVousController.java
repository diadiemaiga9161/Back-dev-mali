package com.Projet.Projet.RendezVous;

import com.Projet.Projet.Message.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8100"}, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("api/rdv")
public class RendezVousController {
    @Autowired
    private RendezVousService rendezVousService;

    //AJOUTER UN RDV
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter")
    public Object Ajouter(@RequestBody RendezVous rendezVous){
        return rendezVousService.Ajouter(rendezVous);
    }

    //AFFICHER LES MESSAGES
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<RendezVous> Afficher(){
        return rendezVousService.Afficher();
    }

    //MODIFIER UN MESSAGE
    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficherparId/{id}")
    public RendezVous AfficherParId(@PathVariable("id") Long id){
        return rendezVousService.AfficherParId(id);
    }

}
