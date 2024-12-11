package com.Projet.Projet.ExperienceProfessionnelle;


import com.Projet.Projet.utilisateur.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.utilisateur.User.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class ExperienceProfessionnelleImple implements ExperienceProfessionnelleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  ExperienceProfessionnelleRepository experienceProfessionnelleRepository;


    @Override
    public  List<Map<String, Object>> getExperienceProfessionnelleByUser() {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        // Obtenir l'utilisateur à partir de la base de données en fonction de l'username
        Optional<User> userOptional = userRepository.findByEmail(currentUsername);
        // Récupérer les expériences professionnelles de l'utilisateur
        List<ExperienceProfessionnelle> experienceProfessionnelles = experienceProfessionnelleRepository.findByUser(userOptional.get());
        List<Map<String, Object>> result = new ArrayList<>();

        for (ExperienceProfessionnelle experience : experienceProfessionnelles) {
            Map<String, Object> experienceMap = new HashMap<>();
            experienceMap.put("id", experience.getId());
            experienceMap.put("titre", experience.getTitre());
            experienceMap.put("datedebut", experience.getDatedebut());
            experienceMap.put("datefin", experience.getDatefin());
            experienceMap.put("lieux", experience.getLieux());

            result.add(experienceMap);
        }

        return result;
    }


    @Override
    public MessageResponse Supprimer(Long id_ExperienceProfessionnelle) {
        return null;
    }

    @Override
    public ExperienceProfessionnelle Modifier(ExperienceProfessionnelle experienceProfessionnelle) {
        return experienceProfessionnelleRepository.findById(experienceProfessionnelle.getId())
                .map(p->{
                    p.setTitre(experienceProfessionnelle.getTitre());
                    p.setDatefin(experienceProfessionnelle.getDatefin());
                    p.setDatedebut(experienceProfessionnelle.getDatedebut());
                    p.setLieux(experienceProfessionnelle.getLieux());
                    return experienceProfessionnelleRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("type non trouvé !"));
    }

    @Override
    public List<ExperienceProfessionnelle> Afficher() {
        return experienceProfessionnelleRepository.findAll();
    }

    @Override
    public Object Ajouter(ExperienceProfessionnelle experienceProfessionnelle) {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        // Obtenir l'utilisateur à partir de la base de données en fonction de l'username
        Optional<User> userOptional = userRepository.findByEmail(currentUsername);
        if (userOptional.isPresent()) {
            experienceProfessionnelle.setUser(userOptional.get());
            experienceProfessionnelleRepository.save(experienceProfessionnelle);
            return new MessageResponse("Ajouter avec succes", true);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Experience non trouvé", false));
        }
    }


    @Override
    public ExperienceProfessionnelle ExperienceProfessionnelleId(Long id_ExperienceProfessionnelle) {
        return experienceProfessionnelleRepository.findById(id_ExperienceProfessionnelle).get();
    }

    //Modifier les experience prof
    @Override
    public MessageResponse modifierExperienceProfessionnelle(Long id, String titre, String datedebut, String datefin, String lieux) {

        ExperienceProfessionnelle experienceProfessionnelle = new ExperienceProfessionnelle();
        experienceProfessionnelle.setId(id);
        experienceProfessionnelle.setTitre(titre);
        experienceProfessionnelle.setDatedebut(datedebut);
        experienceProfessionnelle.setDatefin(datefin);
        experienceProfessionnelle.setLieux(lieux);

        Modifier(experienceProfessionnelle);

        return new MessageResponse("Experience Professionnelle  Modifie avec succes", true);
    }

}
