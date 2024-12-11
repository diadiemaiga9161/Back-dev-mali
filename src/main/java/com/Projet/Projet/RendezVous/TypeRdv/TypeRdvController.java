package com.Projet.Projet.RendezVous.TypeRdv;

import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.ProjetInformatique.TypeProjet.TypeProjet;
import com.Projet.Projet.ProjetInformatique.TypeProjet.TypeProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:49775"}, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("api/typerdv")
public class TypeRdvController {
    @Autowired
    private TypeRdvService typeRdvService;

    //AJOUTER UN TYPE
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter")
    public Object Ajouter(@RequestBody TypeRdv typeRdv){
        return typeRdvService.Ajouter(typeRdv);
    }

    //AFFICHER LES TYPES
    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<TypeRdv> Afficher(){
        return typeRdvService.Afficher();
    }

    //MODIFIER UN TYPE
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/modifier")
    public MessageResponse Modifier(@RequestBody TypeRdv typeRdv){
        typeRdvService.Modifier(typeRdv);
        return new MessageResponse("Type Modifie avec succes", true);
    }


    //SUPPRIMER UN TYPE
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_Typerdv}")
    public MessageResponse Supprimer(@PathVariable("id_Typerdv") Long id_Typerdv){
        return typeRdvService.Supprimer(id_Typerdv);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficherparId/{id}")
    public TypeRdv AfficherParId(@PathVariable("id") Long id){
        return typeRdvService.AfficherParId(id);
    }


}
