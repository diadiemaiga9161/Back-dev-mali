package com.Projet.Projet.Connaissances;

import com.Projet.Projet.Message.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:63592"}, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("api/connaissance")
public class ConnaissancesController {

    @Autowired
    private ConnaissancesService connaissancesService;

    @GetMapping("/afficher")
    public  List<java.util.Map<String, Object>> Afficher(){
        return connaissancesService.getConnaissanceslByUser();
    }


    @GetMapping("/voirs")
    public  List<Map<String, Object>> voir(){
        return connaissancesService.getConnaissanceslByUser();
    }

//    @GetMapping("/voir")
//    public List<Connaissances> voir(){
//        return connaissancesService.Afficher();
//    }
    //AJOUTER Une connaissance
    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter")
    public Object Ajouter (@RequestBody Connaissances connaissances) throws IOException {
        return connaissancesService.Ajouter(connaissances);
    }

    //MODIFIER UNE EXPERIENCE
//    @PreAuthorize("hasRole('ADMIN')") qui veut dire seule l'admin peut joue sur cette fonction
    @PutMapping("/modifier")
    public MessageResponse modifier(@RequestBody Connaissances connaissances) {
        connaissancesService.Modifier(connaissances);
        return new MessageResponse("Connaissance  Modifie avec succes", true);
    }

    //SUPPRIMER UNE EXPERIENCE PROFESSIONELLE
    //@PreAuthorize("hasRole('ADMIN')") qui veut dire seule l'admin peut joue sur cette fonction
    @DeleteMapping("/supprimer/{id}")
    public MessageResponse Supprimer(@PathVariable("id_connaissances") Long id_connaissances){
        return connaissancesService.Supprimer(id_connaissances);
    }
}
