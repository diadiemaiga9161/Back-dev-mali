package com.Projet.Projet.ProjetInformatique.TypeProjet;

import com.Projet.Projet.Message.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeProjetImple implements TypeProjetService{

    @Autowired
    private TypeProjetRepository typeProjetRepository;

    @Override
    public MessageResponse Supprimer(Long id_typeProjet) {
        typeProjetRepository.deleteById(id_typeProjet);
        return new MessageResponse("Type Supprime avec succes", true);
    }

    @Override
    public TypeProjet Modifier(TypeProjet typeProjet) {
        return typeProjetRepository.findById(typeProjet.getId())
                .map(p->{
                    p.setTypeprojet(typeProjet.getTypeprojet());
                    return typeProjetRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("type non trouvé !"));
    }

    @Override
    public List<TypeProjet> Afficher() {
        return typeProjetRepository.findAll();
    }

    @Override
    public TypeProjet AfficherParId(Long id) {
        return typeProjetRepository.findById(id).get();
    }

    @Override
    public Object Ajouter(TypeProjet typeProjet) {
        TypeProjet typeBanque1 = typeProjetRepository.findByTypeprojet(typeProjet.getTypeprojet());
        if (typeBanque1==null){
            typeProjetRepository.save(typeProjet);
            return new MessageResponse("Type ajoute avec succes", true);
        }else {
            return new MessageResponse("Type existe deja", false);
        }
    }
}
