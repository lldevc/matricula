package com.ceiba.matricula.puerto.dao;

import com.ceiba.matricula.modelo.dto.DtoMatricula;

import java.util.List;

public interface DaoMatricula {

    /**
     * Permite listar matriculas
     * @return las matricula
     */
    List<DtoMatricula> listarMatriculas();

    /**
     * Permite buscar una matricula
     * @param id
     * @return matricula
     */
    DtoMatricula buscarMatricula(Long id);

    /**
     * Permite buscar las matriculas por el id del usuario asociado
     * @param id
     * @return los matricula
     */
    List<DtoMatricula> buscarMatriculasPorIdUsuario(Long id);

    /**
     * Permite buscar las matriculas por el numero de identificacion del usuario asociado
     * @param numeroIdentificacion
     * @return los matricula
     */
    List<DtoMatricula> buscarMatriculasPorNumeroIdentificacionUsuario(Long numeroIdentificacion);
}
