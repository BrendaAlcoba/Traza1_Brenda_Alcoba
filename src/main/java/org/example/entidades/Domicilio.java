package org.example.entidades;
import lombok.*;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
@SuperBuilder
public class Domicilio {
    private Long id;
    private String calle;
    private Integer numero;
    private Integer cp;
    // No hay referencia a Localidad (unidireccional, conexión vía set en Localidad)
}