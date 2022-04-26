package com.ceiba.matricula.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;
import com.ceiba.matricula.puerto.repositorio.RepositorioUsuarioMatricula;

public class ServicioActualizarUsuarioMatricula {

    private static final String EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA = "El no usuario existe en el sistema";

    private final RepositorioUsuarioMatricula repositorioUsuarioMatricula;

    public ServicioActualizarUsuarioMatricula(RepositorioUsuarioMatricula repositorioUsuarioMatricula) {
        this.repositorioUsuarioMatricula = repositorioUsuarioMatricula;
    }

    public void ejecutar(UsuarioMatricula usuarioMatricula) {
        validarExistenciaPrevia(usuarioMatricula);
        this.repositorioUsuarioMatricula.actualizar(usuarioMatricula);
    }

    private void validarExistenciaPrevia(UsuarioMatricula usuarioMatricula) {
        boolean existe = this.repositorioUsuarioMatricula.existePorId(usuarioMatricula.getId());
        if(!existe) {
            throw new ExcepcionDuplicidad(EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

}
