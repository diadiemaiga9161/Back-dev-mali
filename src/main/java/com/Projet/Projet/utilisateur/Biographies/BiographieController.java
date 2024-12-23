package com.Projet.Projet.utilisateur.Biographies;


import com.Projet.Projet.Message.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:49775"}, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("api/biographie")
public class BiographieController {

    @Autowired
    private BiographieService biographieService;


    @GetMapping("/afficher")
    public List<Biographie> Afficher(){
        return biographieService.Afficher();
    }

    //AJOUTER Une Biographie
    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter")
    public Object Ajouter (@RequestBody Biographie biographie) throws IOException {
        return biographieService.Ajouter(biographie);
    }

    @GetMapping("/voir")
    public  List<Map<String, Object>> voir(){
        return biographieService.getBiographieByUser();
    }


    //MODIFIER UNE biographie
    //@PreAuthorize("hasRole('ADMIN')") qui veut dire seule l'admin peut joue sur cette fonction
    @PutMapping("/modifier")
    public MessageResponse modifier(@RequestBody Biographie biographie) {
        biographieService.Modifier(biographie);
        return new MessageResponse("Biographie   Modifie avec succes", true);
    }

    //SUPPRIMER UNE  Biographie
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_biographie}")
    public MessageResponse Supprimer(@PathVariable("id_biographie") Long id_biographie){
        return biographieService.Supprimer(id_biographie);
    }
}
