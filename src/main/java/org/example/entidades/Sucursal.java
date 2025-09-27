package org.example.entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class Sucursal {
    private Long id;
    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean esCasaMatriz;



    private Domicilio domicilio; //Una sucursal tiene un domicilio
    // No hay referencia a Empresa (unidireccional, conexión vía set en Empresa)
}