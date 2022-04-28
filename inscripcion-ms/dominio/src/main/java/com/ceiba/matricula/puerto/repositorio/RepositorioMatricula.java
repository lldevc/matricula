package com.ceiba.matricula.puerto.repositorio;

import com.ceiba.matricula.modelo.entidad.Matricula;

public interface RepositorioMatricula {
    /**
     * Permite crear un matricula
     * @param matricula
     * @return el id generado
     */
    Long crear(Matricula matricula);

    /**
     * Permite actualizar un matricula
     * @param matricula
     */
    void actualizar(Matricula matricula);

    /**
     * Permite eliminar un matricula
     * @param id
     */
    void eliminar(Long id);

    /**
     * Permite validar si existe un matricula con un nombre excluyendo un id
     * @return si existe o no
     */
    boolean existePorId(Long id);

}
