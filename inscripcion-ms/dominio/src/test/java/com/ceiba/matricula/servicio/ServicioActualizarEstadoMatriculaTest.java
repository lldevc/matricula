package com.ceiba.matricula.servicio;

import com.ceiba.matricula.modelo.dto.DtoMatricula;
import com.ceiba.matricula.modelo.entidad.*;
import com.ceiba.matricula.puerto.repositorio.RepositorioMatricula;
import com.ceiba.matricula.puerto.repositorio.RepositorioUsuarioMatricula;
import com.ceiba.matricula.servicio.testdatabuilder.MatriculaTestDataBuilder;
import com.ceiba.matricula.servicio.testdatabuilder.ProgramaTestDataBuilder;
import com.ceiba.matricula.servicio.testdatabuilder.TarjetaDeCredioTestDataBuilder;
import com.ceiba.matricula.servicio.testdatabuilder.UsuarioMatriculaTestDataBuilder;
import com.ceiba.matricula.util.MapperMatricula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServicioActualizarEstadoMatriculaTest {

    Programa programa;
    UsuarioMatricula usuario;
    Matricula matricula;
    TarjetaDeCredito tarjetaDeCredito;
    RepositorioMatricula repositorioMatricula;
    RepositorioUsuarioMatricula repositorioUsuarioMatricula;

    @BeforeEach
    void setUp() {
        programa = new ProgramaTestDataBuilder().build();
        usuario = new UsuarioMatriculaTestDataBuilder().build();
        matricula = new MatriculaTestDataBuilder(programa, usuario).conId(1L).build();
        tarjetaDeCredito = new TarjetaDeCredioTestDataBuilder().build();
        repositorioMatricula = Mockito.mock(RepositorioMatricula.class);
        repositorioUsuarioMatricula = Mockito.mock(RepositorioUsuarioMatricula.class);
    }

    @Test
    @DisplayName("Deberia actualizar correctamente el repositorio de matricula y usuario")
    void deberiaActualizarCorrectamenteRepositorioMatriculaYUsuarioMatricula() {
        // arrange
        Matricula matricula1 = new MatriculaTestDataBuilder(programa, usuario)
                .conId(2L)
                .conFechaCreacion(LocalDateTime.of(2022, 4, 3,0,0))
                .conFechaLimitePagoConRecargo(LocalDateTime.of(2022, 4, 8,23,59))
                .conFechaMaximaPago(LocalDateTime.of(2022, 4, 15,23,59))
                .build();
        List<DtoMatricula> matriculas = new ArrayList<>();
        matriculas.add(MapperMatricula.mapperMatriculaToDtoMatricula(matricula1));
        ServicioActualizarEstadoMatricula servicioActualizarEstadoMatricula = new ServicioActualizarEstadoMatricula(repositorioMatricula, repositorioUsuarioMatricula);
        // act
        servicioActualizarEstadoMatricula.ejecutar(matriculas);
        //assert
        Mockito.verify(repositorioMatricula,Mockito.times(1)).actualizar(Mockito.any());
        Mockito.verify(repositorioUsuarioMatricula,Mockito.times(1)).actualizar(Mockito.any());
    }

    @Test
    @DisplayName("Deberia actualizar correctamente el repositorio de matricula")
    void deberiaActualizarCorrectamenteRepositorioMatricula() {
        // arrange
        Matricula matricula1 = new MatriculaTestDataBuilder(programa, usuario)
                .conId(2L)
                .conFechaCreacion(LocalDateTime.now().minusDays(9))
                .conFechaLimitePagoConRecargo(MatriculaTestDataBuilder.calcularFechaLimitePago(LocalDateTime.now().minusDays(9), programa.getDiasParaRecargo()))
                .conFechaMaximaPago(MatriculaTestDataBuilder.calcularFechaLimitePago(LocalDateTime.now().minusDays(9), programa.getDiasParaRecargo() + 5))
                .build();
        List<DtoMatricula> matriculas = new ArrayList<>();
        matriculas.add(MapperMatricula.mapperMatriculaToDtoMatricula(matricula1));
        ServicioActualizarEstadoMatricula servicioActualizarEstadoMatricula = new ServicioActualizarEstadoMatricula(repositorioMatricula, repositorioUsuarioMatricula);
        // act
        servicioActualizarEstadoMatricula.ejecutar(matriculas);
        //assert
        Mockito.verify(repositorioMatricula,Mockito.times(1)).actualizar(Mockito.any());
        Mockito.verify(repositorioUsuarioMatricula,Mockito.times(0)).actualizar(Mockito.any());
    }
}
