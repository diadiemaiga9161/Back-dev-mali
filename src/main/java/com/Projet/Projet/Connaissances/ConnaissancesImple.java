package com.Projet.Projet.Connaissances;

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
public class ConnaissancesImple implements ConnaissancesService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  ConnaissancesRepository connaissancesRepository;

    @Override
    public MessageResponse Supprimer(Long id_connaissances) {
        connaissancesRepository.deleteById(id_connaissances);
        return new MessageResponse("Type Supprime avec succes", true);
    }
    @Override
    public Connaissances Modifier(Connaissances connaissances) {
        return connaissancesRepository.findById(connaissances.getId())
                .map(p->{
                    p.setNom(connaissances.getNom());
                    return connaissancesRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("connaissence non trouvé !"));
    }
    @Override
    public List<Connaissances> Afficher() {
        return connaissancesRepository.findAll();
    }


    @Override
    public List<java.util.Map<String, Object>> getConnaissanceslByUser() {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        // Obtenir l'utilisateur à partir de la base de données en fonction de l'username
        Optional<User> userOptional = userRepository.findByEmail(currentUsername);
        List<Connaissances> connaissances = connaissancesRepository.findByUser(userOptional.get());
        List<java.util.Map<String, Object>> result = new ArrayList<>();

        for (Connaissances connaissance : connaissances) {
            Map<String, Object> connaissancesMap = new HashMap<>();
            connaissancesMap.put("id", connaissance.getId());
            connaissancesMap.put("nom", connaissance.getNom());
//            connaissancesMap.put("type", connaissance.getType());
            connaissancesMap.put("typeConnaissances", connaissance.getTypeConnaissances());
            // Ajoutez d'autres champs si nécessaire

            result.add(connaissancesMap);
        }

        return result;
    }


    @Override
    public Object Ajouter(Connaissances connaissances) {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        // Obtenir l'utilisateur à partir de la base de données en fonction de l'username
        Optional<User> userOptional = userRepository.findByEmail(currentUsername);
        if (userOptional.isPresent()) {
//            connaissances.setUser(userOptional.get());
            connaissancesRepository.save(connaissances);
            return new MessageResponse("Ajouter avec succes", true);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Connaissance non trouvé", false));
        }
    }

    @Override
    public Connaissances ConnaissancesId(Long id_Connaissances) {
        return connaissancesRepository.findById(id_Connaissances).get();
    }

    @Override
    public MessageResponse modifierConnaissances(Long id, String nom) {
        Connaissances connaissances = new Connaissances();
        connaissances.setId(id);
        connaissances.setNom(nom);

        Modifier(connaissances);

        return new MessageResponse("Connaissance  Modifie avec succes", true);
    }
}

