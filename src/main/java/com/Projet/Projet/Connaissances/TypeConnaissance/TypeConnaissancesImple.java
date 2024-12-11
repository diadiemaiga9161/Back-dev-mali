package com.Projet.Projet.Connaissances.TypeConnaissance;

import com.Projet.Projet.Message.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeConnaissancesImple implements TypeConnaissancesService {
    @Autowired
    private TypeConnaissancesRepository typeConnaissancesRepository;
    @Override
    public MessageResponse Supprimer(Long id_typeConnaissances) {
        typeConnaissancesRepository.deleteById(id_typeConnaissances);
        return new MessageResponse("Type Supprime avec succes", true);
    }
    @Override
    public TypeConnaissances Modifier(TypeConnaissances typeConnaissances) {
        return typeConnaissancesRepository.findById(typeConnaissances.getId())
                .map(p->{
                    p.setTypeConnaissances(typeConnaissances.getTypeConnaissances());
                    return typeConnaissancesRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("type non trouvé !"));
    }

    @Override
    public List<TypeConnaissances> Afficher() {
        return typeConnaissancesRepository.findAll();
    }

    @Override
    public Object AfficherParId(Long id) {
        Optional<TypeConnaissances> optionalTypeConnaissances = typeConnaissancesRepository.findById(id);

        if (optionalTypeConnaissances.isPresent()) {
            TypeConnaissances typeConnaissances = optionalTypeConnaissances.get();
            return typeConnaissances;
        } else {
            return new MessageResponse("Cet Identifiant n'existe pas", false);

        }
    }
    @Override
    public Object Ajouter(TypeConnaissances typeConnaissances) {
        TypeConnaissances typeConnaissances1 = typeConnaissancesRepository.findByTypeConnaissances(typeConnaissances.getTypeConnaissances());
        if (typeConnaissances1==null){
            typeConnaissancesRepository.save(typeConnaissances);
            return new MessageResponse("Type ajoute avec succes", true);
        }else {
            return new MessageResponse("Type existe deja", false);
        }
    }
}
