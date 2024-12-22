package com.Projet.Projet.utilisateur.Biographies;

import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.utilisateur.User.User;
import com.Projet.Projet.utilisateur.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BiographieImple  implements  BiographieService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BiographieRepository biographieRepository;

    @Override
    public MessageResponse Supprimer(Long id_biographie) {
        biographieRepository.deleteById(id_biographie);
        return new MessageResponse("Biographie Supprimee avec succes", true);
    }

    @Override
    public Biographie Modifier(Biographie biographie) {
        return biographieRepository.findById(biographie.getId())
                .map(p->{
                    p.setBiographie(biographie.getBiographie());
                    return biographieRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Commentaire non trouvé !"));
    }

    @Override
    public  List<Map<String, Object>> getBiographieByUser() {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        // Obtenir l'utilisateur à partir de la base de données en fonction de l'username
        Optional<User> userOptional = userRepository.findByEmail(currentUsername);
        // Récupérer les expériences professionnelles de l'utilisateur
        List<Biographie> biographies = biographieRepository.findByUser(userOptional.get());
        List<Map<String, Object>> result = new ArrayList<>();

        for (Biographie biographie : biographies) {
            Map<String, Object> bioMap = new HashMap<>();
            bioMap.put("id", biographie.getId());
            bioMap.put("biographie", biographie.getBiographie());


            result.add(bioMap);
        }

        return result;
    }

    @Override
    public List<Biographie> Afficher() {
        return biographieRepository.findAll();
    }

    @Override
    public Object Ajouter(Biographie biographie) {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        // Obtenir l'utilisateur à partir de la base de données en fonction de l'username
        Optional<User> userOptional = userRepository.findByEmail(currentUsername);
        if (userOptional.isPresent()) {
            biographie.setUser(userOptional.get());
            biographieRepository.save(biographie);
            return new MessageResponse("Ajouter avec succes", true);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Experience non trouvé", false));
        }
    }


    @Override
    public Biographie AfficherParId(Long id) {
        return biographieRepository.findById(id).get();
    }
}
