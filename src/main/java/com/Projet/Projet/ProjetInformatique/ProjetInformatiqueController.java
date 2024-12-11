package com.Projet.Projet.ProjetInformatique;

import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.ProjetInformatique.TypeProjet.TypeProjet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:49775"}, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("api/projetInformatique")
public class ProjetInformatiqueController {

    @Autowired
    private ProjetInformatiqueService projetInformatiqueService;

    //AJOUTER UN PROJET
   //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter")
    public Object Ajouter(@RequestParam("titre") String titre, @RequestParam("description") String description,
                          @RequestParam("typeProjet") TypeProjet typeProjet, @RequestParam("photo") MultipartFile photo) throws IOException {
        return projetInformatiqueService.Ajouter(titre, description,typeProjet,photo);
    }

    //AFFICHER LES PROJETS
//    @PreAuthorize("hasRole('ADMIN')")qui veut dire seule l'admin peut joue sur cette fonction
    @GetMapping("/afficher")
    public List<ProjetInformatique> Afficher(){
        return projetInformatiqueService.Afficher();
    }
    //AFFICHER LES PROJETS par user connecter

    @GetMapping("/voir")
    public  List<Map<String, Object>> voir(){
        return projetInformatiqueService.getProjetInformatiquelByUser();
    }


    //MODIFIER UN PROJET
//    @PreAuthorize("hasRole('ADMIN')")qui veut dire seule l'admin peut joue sur cette fonction
    @PutMapping("modifier/{id}")
    public ResponseEntity<MessageResponse> modifierProjet(
            @PathVariable Long id,
            @RequestParam("titre") String titre,
            @RequestParam("description") String description,
            @RequestParam("typeProjet") TypeProjet typeProjet,
            @RequestParam("photo") MultipartFile photo) {
        MessageResponse response = projetInformatiqueService.modifierProjetInformatique(id, titre, description, typeProjet, photo);
        return ResponseEntity.ok(response);
    }

    //SUPPRIMER UN PROJET
//    @PreAuthorize("hasRole('ADMIN')")qui veut dire seule l'admin peut joue sur cette fonction
    @DeleteMapping("/supprimer/{id_projet}")
    public MessageResponse Supprimer(@PathVariable("id_projet") Long id_projet){
        return projetInformatiqueService.Supprimer(id_projet);
    }

    // LA METHODE PERMETTANT D'AFFICHER UN PROJET EN FONCTION DE SON ID
    @GetMapping("/projetparid/{id_projet}")
    public ProjetInformatique AfficherAstuceParId(@PathVariable Long id_projet){
        return projetInformatiqueService.ProjetInformatiqueparId(id_projet);
    }
}