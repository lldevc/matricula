package com.ceiba.matricula.servicio;

import com.ceiba.matricula.puerto.repositorio.RepositorioMatricula;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioEliminarMatriculaTest {

    @Test
    @DisplayName("Deberia eliminar la matricula llamando al repositorio")
    void deberiaEliminarLaMatriculaLlamandoAlRepositorio() {
        RepositorioMatricula repositorioMatricula = Mockito.mock(RepositorioMatricula.class);
        ServicioEliminarMatricula servicioEliminarMatricula = new ServicioEliminarMatricula(repositorioMatricula);

        servicioEliminarMatricula.ejecutar(1L);

        Mockito.verify(repositorioMatricula, Mockito.times(1)).eliminar(1L);

    }

}
