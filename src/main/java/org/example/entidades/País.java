package org.example.entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.HashSet;


@Getter
@Setter
@SuperBuilder
@ToString(exclude = "provincias")
@NoArgsConstructor
@AllArgsConstructor


public class País {
    private Long id;
    private String nombre;

    @Builder.Default
    private Set<Provincia> provincias = new HashSet<>();
}

