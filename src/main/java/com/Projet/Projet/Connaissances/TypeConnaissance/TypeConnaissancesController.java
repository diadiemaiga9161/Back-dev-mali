package com.Projet.Projet.Connaissances.TypeConnaissance;

import com.Projet.Projet.Message.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:49775"}, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("api/typeconnaissance")
public class TypeConnaissancesController {

    @Autowired
    private TypeConnaissancesService typeConnaissancesService;

    //AJOUTER UN TYPE
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter")
    public Object Ajouter(@RequestBody TypeConnaissances TypeConnaissances){
        return typeConnaissancesService.Ajouter(TypeConnaissances);
    }

    //AFFICHER LES TYPES
//     @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<TypeConnaissances> Afficher(){
        return typeConnaissancesService.Afficher();
    }

    //MODIFIER UN TYPE
   // @PreAuthorize("hasRole('ADMIN')")qui veut dire seule l'admin peut joue sur cette fonction
    @PutMapping("/modifier")
    public MessageResponse Modifier(@RequestBody TypeConnaissances TypeConnaissances){
        typeConnaissancesService.Modifier(TypeConnaissances);
        return new MessageResponse("Type Modifie avec succes", true);
    }


    //SUPPRIMER UN TYPE
    //@PreAuthorize("hasRole('ADMIN')")qui veut dire seule l'admin peut joue sur cette fonction
    @DeleteMapping("/supprimer/{id_TypeConnaissances}")
    public MessageResponse Supprimer(@PathVariable("id_TypeConnaissances") Long id_TypeConnaissances){
        return typeConnaissancesService.Supprimer(id_TypeConnaissances);
    }

    //@PreAuthorize("hasRole('ADMIN')")qui veut dire seule l'admin peut joue sur cette fonction
    @GetMapping("/afficherparId/{id}")
    public Object AfficherParId(@PathVariable("id") Long id){
        return typeConnaissancesService.AfficherParId(id);
    }

}
