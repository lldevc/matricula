package com.ceiba.matricula.puerto.repositorio;

import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;

public interface RepositorioUsuarioMatricula {
    /**
     * Permite crear un usuario
     * @param usuario
     * @return el id generado
     */
    Long crear(UsuarioMatricula usuario);

    /**
     * Permite actualizar un usuario
     * @param usuario
     */
    void actualizar(UsuarioMatricula usuario);

    /**
     * Permite eliminar un usuario
     * @param id
     */
    void eliminar(Long id);

    /**
     * Permite validar si existe un usuario con un nombre
     * @param nombre
     * @return si existe o no
     */
    boolean existe(String nombre);

    /**
     * Permite validar si existe un usuario con un nombre excluyendo un id
     * @return si existe o no
     */
    boolean existePorId(Long id);

    /**
     * Permite validar si existe un usuario con un nombre excluyendo un id
     * @return si existe o no
     */
    boolean existePorNumeroIdentificacion(Long id);

    /**
     * Permite validar si un usuario tiene una matricula con determinado programa
     * @param nombre de progama
     * @return si tiene o no
     */
    boolean tieneMatricula(Long id, String nombre);

}
