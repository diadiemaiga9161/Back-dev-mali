package com.Projet.Projet.utilisateur.Specialite;

import com.Projet.Projet.Message.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialiteServiceImple implements SpecialiteService{

    @Autowired
    SpecialiteRepository specialiteRepository;

    @Override
    public MessageResponse Supprimer(Long id_specialite) {
        specialiteRepository.deleteById(id_specialite);
        return new MessageResponse("Specialite Supprimee avec succes", true);
    }


    @Override
    public Specialite Modifier(Specialite specialite) {
        return specialiteRepository.findById(specialite.getId())
                .map(p->{
                    p.setSpecialite(specialite.getSpecialite());
                    return specialiteRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("specialite non trouvé !"));
    }

    @Override
    public List<Specialite> Afficher() {
        return specialiteRepository.findAll();
    }

    @Override
    public Object Ajouter(Specialite specialite) {
        Specialite specialites = specialiteRepository.findBySpecialite(specialite.getSpecialite());
        if (specialites==null){
            specialiteRepository.save(specialite);
            return new MessageResponse("Specialite ajoutee avec succes", true);
        }else {
            return new MessageResponse("Specialite existe deja", false);
        }
    }

    @Override
    public Specialite AfficherParId(Long id) {
        return specialiteRepository.findById(id).get();
    }
}
