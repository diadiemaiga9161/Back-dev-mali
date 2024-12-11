package com.Projet.Projet.utilisateur.User;


import com.Projet.Projet.utilisateur.Role.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean existsByTelephone(String telephone);

    Boolean existsByEmail(String email);

    List<User> findByRoles_Name(ERole roleName);

}
