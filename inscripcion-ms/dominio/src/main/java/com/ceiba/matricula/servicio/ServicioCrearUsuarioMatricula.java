package com.ceiba.matricula.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;
import com.ceiba.matricula.puerto.dao.DaoUsuarioMatricula;
import com.ceiba.matricula.puerto.repositorio.RepositorioUsuarioMatricula;


public class ServicioCrearUsuarioMatricula {

    private static final String EL_USUARIO_YA_EXISTE_EN_EL_SISTEMA = "El usuario ya existe en el sistema";
    private static final String EL_USUARIO_YA_TIENE_UNA_MATRICULA_PARA_ESTE_PROGRAMA = "El usuario ya tiene una matricula registrada para el programa de %s";

    private final RepositorioUsuarioMatricula repositorioUsuarioMatricula;
    private final DaoUsuarioMatricula daoUsuarioMatricula;

    public ServicioCrearUsuarioMatricula(RepositorioUsuarioMatricula repositorioUsuarioMatricula, DaoUsuarioMatricula daoUsuarioMatricula) {
        this.repositorioUsuarioMatricula = repositorioUsuarioMatricula;
        this.daoUsuarioMatricula = daoUsuarioMatricula;
    }

    public Long ejecutar(UsuarioMatricula usuarioMatricula) {
        validarExistenciaPrevia(usuarioMatricula);
        return this.repositorioUsuarioMatricula.crear(usuarioMatricula);
    }

    public Long validarSiUsuarioTieneMatricula(Long numeroIdentificacion, String nombrePrograma) {
        Long usuarioId = daoUsuarioMatricula.listarPorNumeroIdentificacion(numeroIdentificacion).getId();
        boolean tieneMatricula = this.repositorioUsuarioMatricula.tieneMatricula(usuarioId, nombrePrograma);
        if(tieneMatricula) {
            throw new ExcepcionDuplicidad(String.format(EL_USUARIO_YA_TIENE_UNA_MATRICULA_PARA_ESTE_PROGRAMA, nombrePrograma));
        }
        return usuarioId;
    }

    public boolean validarEstaRegistrado(UsuarioMatricula usuarioMatricula) {
        return this.repositorioUsuarioMatricula.existePorNumeroIdentificacion(usuarioMatricula.getNumeroIdentificacion());
    }

    private void validarExistenciaPrevia(UsuarioMatricula usuarioMatricula) {
        boolean existe = this.repositorioUsuarioMatricula.existePorNumeroIdentificacion(usuarioMatricula.getNumeroIdentificacion());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_USUARIO_YA_EXISTE_EN_EL_SISTEMA);
        }
    }

}
