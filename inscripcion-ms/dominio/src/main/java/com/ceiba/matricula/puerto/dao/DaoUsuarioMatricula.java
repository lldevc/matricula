package com.ceiba.matricula.puerto.dao;

import com.ceiba.matricula.modelo.dto.DtoUsuarioMatricula;

import java.util.List;

public interface DaoUsuarioMatricula {

    /**
     * Permite listar usuarios
     * @return los usuarios
     */
    List<DtoUsuarioMatricula> listar();

    /**
     * Permite listar usuarios
     * @return los usuarios
     */
    DtoUsuarioMatricula listarPorId(Long id);

    /**
     * Permite listar usuarios por numero de identidicacion
     * @return usuarios
     */
    DtoUsuarioMatricula listarPorNumeroIdentificacion(Long id);
}
