package com.ceiba.matricula.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.matricula.modelo.dto.DtoUsuarioMatricula;
import com.ceiba.matricula.modelo.entidad.Programa;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;
import com.ceiba.matricula.puerto.dao.DaoUsuarioMatricula;
import com.ceiba.matricula.puerto.repositorio.RepositorioUsuarioMatricula;
import com.ceiba.matricula.servicio.testdatabuilder.ProgramaTestDataBuilder;
import com.ceiba.matricula.servicio.testdatabuilder.UsuarioMatriculaTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServicioCrearUsuarioMatriculaTest {

    UsuarioMatricula usuario;
    Programa programa;
    RepositorioUsuarioMatricula repositorioUsuarioMatricula;
    DaoUsuarioMatricula daoUsuarioMatricula;

    @BeforeEach
    void setUp() {
        usuario = new UsuarioMatriculaTestDataBuilder().build();
        programa = new ProgramaTestDataBuilder().build();
        repositorioUsuarioMatricula = Mockito.mock(RepositorioUsuarioMatricula.class);
        daoUsuarioMatricula = Mockito.mock(DaoUsuarioMatricula.class);
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del Usuario")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDelUsuario() {
        // arrange
        Mockito.when(repositorioUsuarioMatricula.existePorNumeroIdentificacion(Mockito.anyLong())).thenReturn(true);
        ServicioCrearUsuarioMatricula servicioCrearUsuarioMatricula = new ServicioCrearUsuarioMatricula(repositorioUsuarioMatricula, daoUsuarioMatricula);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearUsuarioMatricula.ejecutar(usuario), ExcepcionDuplicidad.class,"El usuario ya existe en el sistema");
    }

    @Test
    @DisplayName("Deberia Crear el usuario de manera correcta")
    void deberiaCrearElUsuarioDeManeraCorrecta() {
        // arrange
        Mockito.when(repositorioUsuarioMatricula.crear(usuario)).thenReturn(usuario.getId());
        ServicioCrearUsuarioMatricula servicioCrearUsuarioMatricula = new ServicioCrearUsuarioMatricula(repositorioUsuarioMatricula, daoUsuarioMatricula);
        // act
        Long idUsuario = servicioCrearUsuarioMatricula.ejecutar(usuario);
        //- assert
        assertEquals(1234,idUsuario);
        Mockito.verify(repositorioUsuarioMatricula, Mockito.times(1)).crear(usuario);
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide que el usuario ya esta matriculado en determinado programa")
    void deberiaLanzarUnaExepcionCuandoSeValideLaMatriculaDelUsuario() {
        // arrange
        DtoUsuarioMatricula dtoUsuarioMatricula = new DtoUsuarioMatricula(
                usuario.getId(),
                usuario.getNumeroIdentificacion(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getCiudad(),
                usuario.getDireccion(),
                usuario.getFechaSancion()
        );
        Mockito.when(daoUsuarioMatricula.listarPorNumeroIdentificacion(Mockito.anyLong())).thenReturn(dtoUsuarioMatricula);
        Mockito.when(repositorioUsuarioMatricula.tieneMatricula(dtoUsuarioMatricula.getId(), programa.getNombre())).thenReturn(true);
        ServicioCrearUsuarioMatricula servicioCrearUsuarioMatricula = new ServicioCrearUsuarioMatricula(repositorioUsuarioMatricula, daoUsuarioMatricula);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearUsuarioMatricula.validarSiUsuarioTieneMatricula(usuario.getNumeroIdentificacion(), programa.getNombre()), ExcepcionDuplicidad.class,"El usuario ya tiene una matricula registrada para el programa de "+programa.getNombre());
    }

    @Test
    @DisplayName("Deberia validar que un usuario esta registrado")
    void deberiaValidarUsuarioRegistrado() {
        // arrange
        Mockito.when(repositorioUsuarioMatricula.existePorNumeroIdentificacion(Mockito.anyLong())).thenReturn(true);
        ServicioCrearUsuarioMatricula servicioCrearUsuarioMatricula = new ServicioCrearUsuarioMatricula(repositorioUsuarioMatricula, daoUsuarioMatricula);
        // act -
        boolean existe = servicioCrearUsuarioMatricula.validarEstaRegistrado(usuario);
        //- assert
        assertTrue(existe);
        Mockito.verify(repositorioUsuarioMatricula, Mockito.times(1)).existePorNumeroIdentificacion(usuario.getId());
    }

    @Test
    @DisplayName("Deberia devolver el id del usuario esta registrado")
    void deberiaDevolverIdUsuarioRegistrado() {
        // arrange
        DtoUsuarioMatricula dtoUsuarioMatricula = new DtoUsuarioMatricula(
                usuario.getId(),
                usuario.getNumeroIdentificacion(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getCiudad(),
                usuario.getDireccion(),
                usuario.getFechaSancion()
        );
        Mockito.when(daoUsuarioMatricula.listarPorNumeroIdentificacion(Mockito.anyLong())).thenReturn(dtoUsuarioMatricula);
        Mockito.when(repositorioUsuarioMatricula.tieneMatricula(dtoUsuarioMatricula.getId(), programa.getNombre())).thenReturn(false);
        ServicioCrearUsuarioMatricula servicioCrearUsuarioMatricula = new ServicioCrearUsuarioMatricula(repositorioUsuarioMatricula, daoUsuarioMatricula);
        // act -
        Long idUsuario = servicioCrearUsuarioMatricula.validarSiUsuarioTieneMatricula(usuario.getNumeroIdentificacion(), programa.getNombre());
        //- assert
        assertEquals(1234, idUsuario);
        Mockito.verify(repositorioUsuarioMatricula, Mockito.times(1)).tieneMatricula(idUsuario, programa.getNombre());
        Mockito.verify(daoUsuarioMatricula, Mockito.times(1)).listarPorNumeroIdentificacion(usuario.getNumeroIdentificacion());
    }
}
