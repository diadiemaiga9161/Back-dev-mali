package com.Projet.Projet.ProjetInformatique.TypeProjet;

import com.Projet.Projet.Message.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8100"}, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("api/typeprojet")
public class TypeProjetController {
    @Autowired
    private TypeProjetService typeProjetService;

    //AJOUTER UN TYPE
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter")
    public Object Ajouter(@RequestBody TypeProjet TypeProjet){
        return typeProjetService.Ajouter(TypeProjet);
    }

    //AFFICHER LES TYPES
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<TypeProjet> Afficher(){
        return typeProjetService.Afficher();
    }

    //MODIFIER UN TYPE
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/modifier")
    public MessageResponse Modifier(@RequestBody TypeProjet TypeProjet){
        typeProjetService.Modifier(TypeProjet);
        return new MessageResponse("Type Modifie avec succes", true);
    }


    //SUPPRIMER UN TYPE
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_TypeProjet}")
    public MessageResponse Supprimer(@PathVariable("id_TypeProjet") Long id_TypeProjet){
        return typeProjetService.Supprimer(id_TypeProjet);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficherparId/{id}")
    public TypeProjet AfficherParId(@PathVariable("id") Long id){
        return typeProjetService.AfficherParId(id);
    }


}
