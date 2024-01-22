package com.Projet.Projet.RendezVous;

import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.ProjetInformatique.ProjetInformatiqueRepository;
import com.Projet.Projet.utilisateur.User.User;
import com.Projet.Projet.utilisateur.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RendezVousImple implements RendezVousService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Override
    public MessageResponse Supprimer(Long id_message) {
        rendezVousRepository.deleteById(id_message);
        return new MessageResponse("Rendez-vous Supprime avec succes", true);
    }

    @Override
    public RendezVous Modifier(RendezVous rendezVous) {
        return rendezVousRepository.findById(rendezVous.getId())
                .map(p->{
                    p.setObjet(rendezVous.getObjet());
                    p.setDateRendezvous(rendezVous.getDateRendezvous());
                    p.setHeureRendezvous(rendezVous.getHeureRendezvous());
                    p.setDateenvoie(new Date());
                    return rendezVousRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Rendez-vous non trouvé !"));
    }

    @Override
    public List<RendezVous> Afficher() {
        return rendezVousRepository.findAll();
    }

    @Override
    public Object Ajouter(RendezVous rendezVous) {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
//        System.out.println(currentUsername);
        // Obtenir l'utilisateur à partir de la base de données en fonction de l'username
        Optional<User> userOptional = userRepository.findByEmail(currentUsername);
        if (userOptional.isPresent()) {
            rendezVous.setUser(userOptional.get());
            rendezVous.setDateenvoie(new Date());
            rendezVousRepository.save(rendezVous);
            return new MessageResponse("Rendez-vous ajoute avec succes", true);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Utilisateur non trouvé", false));
        }
    }

    @Override
    public RendezVous AfficherParId(Long id) {
        return rendezVousRepository.findById(id).get();
    }
}
