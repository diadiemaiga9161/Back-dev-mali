package com.Projet.Projet.RendezVous;

import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.ProjetInformatique.ProjetInformatiqueRepository;
import com.Projet.Projet.RendezVous.TypeRdv.TypeRdv;
import com.Projet.Projet.RendezVous.TypeRdv.TypeRdvRepository;
import com.Projet.Projet.utilisateur.User.User;
import com.Projet.Projet.utilisateur.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RendezVousImple implements RendezVousService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TypeRdvRepository typeRdvRepository;

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
    public Object Ajouter(RendezVous rendezVous,Long typeRendezVousId, User idUser) {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
//        System.out.println(currentUsername);
        // Obtenir l'utilisateur à partir de la base de données en fonction de l'username
        Optional<User> userOptional = userRepository.findByEmail(currentUsername);
        if (userOptional.isPresent()) {
            rendezVous.setUserEvoyer(userOptional.get());
            rendezVous.setUser(idUser);
            rendezVous.setDateenvoie(new Date());
            // Récupérer le type correspondant
            TypeRdv typeRdv = typeRdvRepository.findById(typeRendezVousId)
                    .orElseThrow(() -> new EntityNotFoundException("Type introuvable avec l'ID: " + typeRendezVousId));
            rendezVous.setTypeRdv(typeRdv);
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

    @Override
    public List<Map<String, Object>> AfficherRdvParRecuParUserConnecter() {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Optional<User> userOptional = userRepository.findByEmail(currentUsername);
        // Vérifier si l'utilisateur existe
        if (userOptional.isPresent()) {
            User utilisateurConnecte = userOptional.get();
            List<RendezVous> rdvsUtilisateur = rendezVousRepository.findByUser(utilisateurConnecte);
            // Initialiser une liste pour stocker les détails des rdv
            List<Map<String, Object>> rdvDetailsList = new ArrayList<>();
            // Parcourir chaque rdv dans la liste
            for (RendezVous rdv : rdvsUtilisateur) {
                // Créer une nouvelle map pour stocker les détails du rdv
                Map<String, Object> rdvDetails = new HashMap<>();
                // Ajouter les détails du rdv dans la map
                rdvDetails.put("id", rdv.getId());
                rdvDetails.put("date", rdv.getDateRendezvous());
                rdvDetails.put("heure", rdv.getHeureRendezvous());
                rdvDetails.put("objet", rdv.getObjet());
                rdvDetails.put("typerdv", rdv.getTypeRdv());
//                rdvDetails.put("telephone", rdv.getTelephone());
                rdvDetails.put("createdAt", rdv.getDateenvoie());
                rdvDetails.put("userenvoyer", rdv.getUserEvoyer());
                // Ajouter les détails du rdv à la liste
                rdvDetailsList.add(rdvDetails);
            }
            // Retourner la liste des détails des rdv
            return rdvDetailsList;
        }else {
            // Gérer le cas où l'utilisateur n'est pas trouvé
            // Vous pouvez lancer une exception, retourner une liste vide ou prendre d'autres mesures selon vos besoins
            throw new RuntimeException("Utilisateur non trouvé");
        }
    }

    @Override
    public List<Map<String, Object>> AfficherRdvParEnvoyerParUserConnecterNew() {
        // Obtenir l'utilisateur connecté à partir de l'objet Authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Optional<User> userOptional = userRepository.findByEmail(currentUsername);
        // Vérifier si l'utilisateur existe
        if (userOptional.isPresent()) {
            User utilisateurConnecte = userOptional.get();
            List<RendezVous> rdvsUtilisateur = rendezVousRepository.findByUserEvoyer(utilisateurConnecte);
            // Initialiser une liste pour stocker les détails des rdv
            List<Map<String, Object>> rdvDetailsList = new ArrayList<>();
            // Parcourir chaque rdv dans la liste
            for (RendezVous rdv : rdvsUtilisateur) {
                // Créer une nouvelle map pour stocker les détails du rdv
                Map<String, Object> rdvDetails = new HashMap<>();
                // Ajouter les détails du rdv dans la map
                rdvDetails.put("id", rdv.getId());
                rdvDetails.put("date", rdv.getDateRendezvous());
                rdvDetails.put("heure", rdv.getHeureRendezvous());
                rdvDetails.put("objet", rdv.getObjet());
                rdvDetails.put("typerdv", rdv.getTypeRdv());
                rdvDetails.put("createdAt", rdv.getDateenvoie());
                rdvDetails.put("user", rdv.getUser());
                // Ajouter les détails du rdv à la liste
                rdvDetailsList.add(rdvDetails);
            }
            // Retourner la liste des détails des rdv
            return rdvDetailsList;
        }else {
            // Gérer le cas où l'utilisateur n'est pas trouvé
            // Vous pouvez lancer une exception, retourner une liste vide ou prendre d'autres mesures selon vos besoins
            throw new RuntimeException("Utilisateur non trouvé");
        }
    }
}
