package com.Projet.Projet.ProjetInformatique;

import com.Projet.Projet.Image.Image;
import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.ProjetInformatique.TypeProjet.TypeProjet;
import com.Projet.Projet.utilisateur.User.User;
import com.Projet.Projet.utilisateur.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ProjetInformatiqueImple implements ProjetInformatiqueService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjetInformatiqueRepository projetInformatiqueRepository;

    @Override
    public MessageResponse Supprimer(Long id_ProjetInformatique) {
        return null;
    }

    @Override
    public ProjetInformatique Modifier(ProjetInformatique projetInformatique) {
        return projetInformatiqueRepository.findById(projetInformatique.getId())
                .map(p->{
                    p.setTitre(projetInformatique.getTitre());
                    p.setDescription(projetInformatique.getDescription());
                    p.setTypeProjet(projetInformatique.getTypeProjet());
                    p.setPhoto(projetInformatique.getPhoto());
                    return projetInformatiqueRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Projet non trouvé !"));
    }

    @Override
    public List<ProjetInformatique> Afficher() {
        return projetInformatiqueRepository.findAll();
    }

    @Override
    public List<ProjetInformatique> getProjetsByUser(User user) {
        return List.of();
    }

    @Override
    public List<java.util.Map<String, Object>> getProjetInformatiquelByUser() {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        // Obtenir l'utilisateur à partir de la base de données en fonction de l'username
        Optional<User> userOptional = userRepository.findByEmail(currentUsername);
        List<ProjetInformatique> projetInformatiques = projetInformatiqueRepository.findByUser(userOptional.get());
        List<java.util.Map<String, Object>> result = new ArrayList<>();

        for (ProjetInformatique projetInformatique : projetInformatiques) {
            java.util.Map<String, Object> projetInformatiqueMap = new HashMap<>();
//            projetInformatiqueMap.put("id", projetInformatiques.getId());
            projetInformatiqueMap.put("datecreation", projetInformatique.getDatecreation());
            projetInformatiqueMap.put("titre", projetInformatique.getTitre());
            projetInformatiqueMap.put("description", projetInformatique.getDescription());
            projetInformatiqueMap.put("photo", projetInformatique.getPhoto());
            projetInformatiqueMap.put("typeProjet", projetInformatique.getTypeProjet());
            // Ajoutez d'autres champs si nécessaire

            result.add(projetInformatiqueMap);
        }

        return result;
    }

    @Override
    public Object Ajouter(String titre, String description, TypeProjet typeProjet, MultipartFile photo) {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        // Obtenir l'utilisateur à partir de la base de données en fonction de l'username
        Optional<User> userOptional = userRepository.findByEmail(currentUsername);

        if (userOptional.isPresent()) {
            ProjetInformatique projetInformatique = new ProjetInformatique();
            projetInformatique.setTitre(titre);
            projetInformatique.setDescription(description);
            projetInformatique.setDatecreation(new Date());
            projetInformatique.setUser(userOptional.get());
            projetInformatique.setTypeProjet(typeProjet);
            int lastDotIndex2 = photo.getOriginalFilename().lastIndexOf(".");
            if (lastDotIndex2 >=0){
                String fileExtension2 = photo.getOriginalFilename().substring(lastDotIndex2);
                String photo2 = "projet_"+ System.currentTimeMillis() + fileExtension2;
                projetInformatique.setPhoto(Image.save(photo, photo2, "Images/Projet"));
            }
            projetInformatiqueRepository.save(projetInformatique);
            return new MessageResponse("Ajouter avec succes", true);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Utilisateur non trouvé", false));
        }

    }

    @Override
    public ProjetInformatique ProjetInformatiqueparId(Long id_ProjetInformatique) {
        return projetInformatiqueRepository.findById(id_ProjetInformatique).get();
    }

    @Override
    public MessageResponse modifierProjetInformatique(Long id, String titre, String description, TypeProjet typeProjet, MultipartFile photo) {
        ProjetInformatique projetInformatique = new ProjetInformatique();
        projetInformatique.setId(id);
        projetInformatique.setTitre(titre);
        projetInformatique.setDescription(description);
        projetInformatique.setTypeProjet(typeProjet);
        int lastDotIndex = photo.getOriginalFilename().lastIndexOf(".");
        if (lastDotIndex >=0){
            String fileExtension = photo.getOriginalFilename().substring(lastDotIndex);
            String photo2 = "projet_"+ System.currentTimeMillis() + fileExtension;
            projetInformatique.setPhoto(Image.save(photo, photo2, "Images/Projet"));
        }

        Modifier(projetInformatique);

        return new MessageResponse("Projet Modifie avec succes", true);
    }
}
