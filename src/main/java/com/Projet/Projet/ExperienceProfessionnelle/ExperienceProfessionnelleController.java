package com.Projet.Projet.ExperienceProfessionnelle;
import com.Projet.Projet.Message.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:49775"}, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("api/amadou")
public class ExperienceProfessionnelleController {

    @Autowired
    private ExperienceProfessionnelleService experienceProfessionnelleService;

    @GetMapping("/afficher")
    public List<ExperienceProfessionnelle> Afficher(){
        return experienceProfessionnelleService.Afficher();
    }

     //AJOUTER Une experience
    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter")
   public Object Ajouter (@RequestBody ExperienceProfessionnelle experienceProfessionnelle) throws IOException {
       return experienceProfessionnelleService.Ajouter(experienceProfessionnelle);
    }

    @GetMapping("/voir")
    public  List<Map<String, Object>> voir(){
        return experienceProfessionnelleService.getExperienceProfessionnelleByUser();
    }


    //MODIFIER UNE EXPERIENCE
     //@PreAuthorize("hasRole('ADMIN')") qui veut dire seule l'admin peut joue sur cette fonction
    @PutMapping("/modifier")
    public MessageResponse modifier(@RequestBody ExperienceProfessionnelle experienceProfessionnelle) {
        experienceProfessionnelleService.Modifier(experienceProfessionnelle);
        return new MessageResponse("Experience Professionnelle  Modifie avec succes", true);
    }

    //SUPPRIMER UNE EXPERIENCE PROFESSIONELLE
   //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_experienceProfessionnelle}")
    public MessageResponse Supprimer(@PathVariable("id_experienceProfessionnelle") Long id_experienceProfessionnelle){
        return experienceProfessionnelleService.Supprimer(id_experienceProfessionnelle);
    }

}
