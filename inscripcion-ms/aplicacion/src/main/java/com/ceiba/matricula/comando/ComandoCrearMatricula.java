package com.ceiba.matricula.comando;

import com.ceiba.matricula.modelo.entidad.Programa;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoCrearMatricula {

    private Programa programa;
    private UsuarioMatricula usuarioMatricula;
}
