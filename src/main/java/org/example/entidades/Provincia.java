package org.example.entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(exclude = "localidades") // Evita recursión infinita
public class Provincia {
    private Long id;
    private String nombre;


    @Builder.Default
    private Set<Localidad> localidades = new HashSet<>(); // 1..* Localidades

}
