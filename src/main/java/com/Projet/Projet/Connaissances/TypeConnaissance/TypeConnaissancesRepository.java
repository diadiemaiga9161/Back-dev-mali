package com.Projet.Projet.Connaissances.TypeConnaissance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeConnaissancesRepository extends JpaRepository<TypeConnaissances, Long> {
    TypeConnaissances findByTypeConnaissances(String typeConnaissances);
}
