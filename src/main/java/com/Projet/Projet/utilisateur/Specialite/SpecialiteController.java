package com.Projet.Projet.utilisateur.Specialite;

import com.Projet.Projet.Message.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:63592"}, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("api/specialite")
public class SpecialiteController {
    @Autowired
    private SpecialiteService specialiteService;

    //AJOUTER UNE SPECIALITE
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter")
    public Object Ajouter(@RequestBody Specialite specialite){
        return specialiteService.Ajouter(specialite);
    }

    //AFFICHER LES SPECIALITES
    @GetMapping("/afficher")
    public List<Specialite> Afficher(){
        return specialiteService.Afficher();
    }

    //MODIFIER UN AGE
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier"})
    public MessageResponse Modifier(@RequestBody Specialite specialite){
        specialiteService.Modifier(specialite);
        return new MessageResponse("SPecialite Modifiee avec succes", true);
    }

    //SUPPRIMER UN AGE
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_specialite}")
    public MessageResponse Supprimer(@PathVariable("id_specialite") Long id_specialite){
        return specialiteService.Supprimer(id_specialite);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficherparId/{id}")
    public Specialite AfficherParId(@PathVariable("id") Long id){
        return specialiteService.AfficherParId(id);
    }

}
