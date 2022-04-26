package com.ceiba.matricula.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoUsuarioMatricula {

    private Long id;
    private Long numeroIdentificacion;
    private String nombre;
    private String email;
    private String ciudad;
    private String direccion;
}
