package com.ceiba.matricula.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.matricula.modelo.entidad.Matricula;
import com.ceiba.matricula.modelo.entidad.Programa;
import com.ceiba.matricula.modelo.entidad.TarjetaDeCredito;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;
import com.ceiba.matricula.puerto.repositorio.RepositorioMatricula;
import com.ceiba.matricula.servicio.testdatabuilder.MatriculaTestDataBuilder;
import com.ceiba.matricula.servicio.testdatabuilder.ProgramaTestDataBuilder;
import com.ceiba.matricula.servicio.testdatabuilder.TarjetaDeCredioTestDataBuilder;
import com.ceiba.matricula.servicio.testdatabuilder.UsuarioMatriculaTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioActualizarMatriculaTest {

    Programa programa;
    UsuarioMatricula usuario;
    Matricula matricula;
    TarjetaDeCredito tarjetaDeCredito;
    RepositorioMatricula repositorioMatricula;

    @BeforeEach
    void setUp() {
        programa = new ProgramaTestDataBuilder().build();
        usuario = new UsuarioMatriculaTestDataBuilder().build();
        matricula = new MatriculaTestDataBuilder(programa, usuario).conId(1L).build();
        tarjetaDeCredito = new TarjetaDeCredioTestDataBuilder().build();
        repositorioMatricula = Mockito.mock(RepositorioMatricula.class);
    }

    @Test
    @DisplayName("Deberia validar la existencia previa del matricula")
    void deberiaValidarLaExistenciaPreviaDeLaMatricula() {
        // arrange
        Mockito.when(repositorioMatricula.existePorId(Mockito.anyLong())).thenReturn(false);
        ServicioActualizarMatricula servicioActualizarMatricula = new ServicioActualizarMatricula(repositorioMatricula);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarMatricula.ejecutar(matricula), ExcepcionDuplicidad.class,"La matricula no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio")
    void deberiaActualizarCorrectamenteElRepositorio() {
        // arrange
        Mockito.when(repositorioMatricula.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarMatricula servicioActualizarMatricula = new ServicioActualizarMatricula(repositorioMatricula);
        // act
        servicioActualizarMatricula.ejecutar(matricula);
        //assert
        Mockito.verify(repositorioMatricula,Mockito.times(1)).actualizar(matricula);
    }
}
