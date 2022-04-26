package com.ceiba.matricula.comando.fabrica;

import com.ceiba.matricula.comando.ComandoUsuarioMatricula;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;
import org.springframework.stereotype.Component;

@Component
public class FabricaUsuarioMatricula {

    public UsuarioMatricula crear(ComandoUsuarioMatricula comandoUsuarioMatricula) {
        return new UsuarioMatricula(
                comandoUsuarioMatricula.getId(),
                comandoUsuarioMatricula.getNumeroIdentificacion(),
                comandoUsuarioMatricula.getNombre(),
                comandoUsuarioMatricula.getEmail(),
                comandoUsuarioMatricula.getCiudad(),
                comandoUsuarioMatricula.getDireccion()
        );
    }

}
