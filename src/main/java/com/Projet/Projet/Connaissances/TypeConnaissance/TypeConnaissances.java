package com.Projet.Projet.Connaissances.TypeConnaissance;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Size;

@Data
@Entity
@Getter
@Setter
@Table(name = "typeconnaissance")
@NoArgsConstructor
@AllArgsConstructor
public class TypeConnaissances {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 200)
    private String typeConnaissances;
}
