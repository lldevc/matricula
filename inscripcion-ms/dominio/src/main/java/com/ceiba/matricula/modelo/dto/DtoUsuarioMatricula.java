package com.ceiba.matricula.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoUsuarioMatricula {
    private Long id;
    private Long numeroIdentificacion;
    private String nombre;
    private String email;
    private String ciudad;
    private String direccion;
    private LocalDateTime fechaSancion;

}
