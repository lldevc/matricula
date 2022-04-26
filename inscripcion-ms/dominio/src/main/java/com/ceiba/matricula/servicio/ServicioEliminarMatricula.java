package com.ceiba.matricula.servicio;

import com.ceiba.matricula.puerto.repositorio.RepositorioMatricula;

public class ServicioEliminarMatricula {

    private final RepositorioMatricula repositorioMatricula;

    public ServicioEliminarMatricula(RepositorioMatricula repositorioMatricula) {
        this.repositorioMatricula = repositorioMatricula;
    }

    public void ejecutar(Long id) {
        this.repositorioMatricula.eliminar(id);
    }
}
