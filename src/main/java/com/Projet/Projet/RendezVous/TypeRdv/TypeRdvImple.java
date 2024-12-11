package com.Projet.Projet.RendezVous.TypeRdv;

import com.Projet.Projet.Message.MessageResponse;
import com.Projet.Projet.ProjetInformatique.TypeProjet.TypeProjet;
import com.Projet.Projet.ProjetInformatique.TypeProjet.TypeProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeRdvImple implements TypeRdvService{
    @Autowired
    private TypeRdvRepository typeRdvRepository;


    @Override
    public MessageResponse Supprimer(Long id_typerdv) {
        typeRdvRepository.deleteById(id_typerdv);
        return new MessageResponse("Type Supprime avec succes", true);
    }

    @Override
    public TypeRdv Modifier(TypeRdv typeRdv) {
        return typeRdvRepository.findById(typeRdv.getId())
                .map(p->{
                    p.setTyperdv(typeRdv.getTyperdv());
                    return typeRdvRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("type non trouvé !"));
    }

    @Override
    public List<TypeRdv> Afficher() {
        return typeRdvRepository.findAll();
    }

    @Override
    public TypeRdv AfficherParId(Long id) {
        return typeRdvRepository.findById(id).get();
    }

    @Override
    public Object Ajouter(TypeRdv typeRdv) {
        TypeRdv typeRdv1 = typeRdvRepository.findByTyperdv(typeRdv.getTyperdv());
        if (typeRdv1==null){
            typeRdvRepository.save(typeRdv1);
            return new MessageResponse("Type ajoute avec succes", true);
        }else {
            return new MessageResponse("Type existe deja", false);
        }
    }
}
