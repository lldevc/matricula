package com.ceiba.matricula.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;
import com.ceiba.matricula.puerto.repositorio.RepositorioUsuarioMatricula;
import com.ceiba.matricula.servicio.testdatabuilder.UsuarioMatriculaTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioActualizarUsuarioMatriculaTest {

    UsuarioMatricula usuario;
    RepositorioUsuarioMatricula repositorioUsuarioMatricula;

    @BeforeEach
    void setUp() {
        usuario = new UsuarioMatriculaTestDataBuilder().build();
        repositorioUsuarioMatricula = Mockito.mock(RepositorioUsuarioMatricula.class);
    }

    @Test
    @DisplayName("Deberia validar la existencia previa del usuario")
    void deberiaValidarLaExistenciaPreviaDeLUsuarioMatricula() {
        // arrange
        Mockito.when(repositorioUsuarioMatricula.existePorNumeroIdentificacion(Mockito.anyLong())).thenReturn(false);
        ServicioActualizarUsuarioMatricula servicioActualizarUsuarioMatricula = new ServicioActualizarUsuarioMatricula(repositorioUsuarioMatricula);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarUsuarioMatricula.ejecutar(usuario), ExcepcionDuplicidad.class,"El no usuario existe en el sistema");
    }


    @Test
    @DisplayName("Deberia actualizar correctamente el repositorio")
    void deberiaActualizarCorrectamenteElRepositorioUsuarioMatricula() {
        // arrange
        Mockito.when(repositorioUsuarioMatricula.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarUsuarioMatricula servicioActualizarUsuarioMatricula = new ServicioActualizarUsuarioMatricula(repositorioUsuarioMatricula);
        // act
        servicioActualizarUsuarioMatricula.ejecutar(usuario);
        // assert
        Mockito.verify(repositorioUsuarioMatricula, Mockito.times(1)).actualizar(usuario);
    }
}
