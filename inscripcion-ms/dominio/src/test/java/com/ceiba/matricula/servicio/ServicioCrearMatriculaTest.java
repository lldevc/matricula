package com.ceiba.matricula.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionUsuarioSancionado;
import com.ceiba.matricula.modelo.dto.DtoUsuarioMatricula;
import com.ceiba.matricula.modelo.entidad.Matricula;
import com.ceiba.matricula.modelo.entidad.Programa;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;
import com.ceiba.matricula.puerto.dao.DaoUsuarioMatricula;
import com.ceiba.matricula.puerto.repositorio.RepositorioMatricula;
import com.ceiba.matricula.servicio.testdatabuilder.MatriculaTestDataBuilder;
import com.ceiba.matricula.servicio.testdatabuilder.ProgramaTestDataBuilder;
import com.ceiba.matricula.servicio.testdatabuilder.UsuarioMatriculaTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearMatriculaTest {

    Programa programa;
    UsuarioMatricula usuario;
    Matricula matricula;
    RepositorioMatricula repositorioMatricula;
    DaoUsuarioMatricula daoUsuarioMatricula;

    @BeforeEach
    void setUp() {
        programa = new ProgramaTestDataBuilder().build();
        usuario = new UsuarioMatriculaTestDataBuilder().build();
        matricula = new MatriculaTestDataBuilder(programa, usuario).conId(1L).build();
        repositorioMatricula = Mockito.mock(RepositorioMatricula.class);
        daoUsuarioMatricula = Mockito.mock(DaoUsuarioMatricula.class);
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia de la matricula")
    void deberiaValidarLaExistenciaPreviaDeLaMatricula() {
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
        Mockito.when(daoUsuarioMatricula.listarPorId(Mockito.anyLong())).thenReturn(dtoUsuarioMatricula);
        Mockito.when(repositorioMatricula.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioCrearMatricula servicioCrearMatricula = new ServicioCrearMatricula(repositorioMatricula, daoUsuarioMatricula);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearMatricula.ejecutar(matricula, programa.getId(), usuario.getId()), ExcepcionDuplicidad.class,"El usuario ya tiene una matricula registrada para el programa de "+ matricula.getPrograma().getNombre()+" en el sistema");
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide que el usuario asociado a la matricula esta sancionado")
    void deberiaValidarSiElUsuarioQueSeEstaMatriculandoEstaSancionado() {
        // arrange
        DtoUsuarioMatricula dtoUsuarioMatricula = new DtoUsuarioMatricula(
                usuario.getId(),
                usuario.getNumeroIdentificacion(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getCiudad(),
                usuario.getDireccion(),
                LocalDateTime.of(3022, 1, 1, 0, 0)
        );
        Mockito.when(repositorioMatricula.existePorId(Mockito.anyLong())).thenReturn(false);
        Mockito.when(daoUsuarioMatricula.listarPorId(Mockito.anyLong())).thenReturn(dtoUsuarioMatricula);
        ServicioCrearMatricula servicioCrearMatricula = new ServicioCrearMatricula(repositorioMatricula, daoUsuarioMatricula);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearMatricula.ejecutar(matricula, programa.getId(), usuario.getId()), ExcepcionUsuarioSancionado.class,"el aspirante esta sancionado por no pago de matricula hasta la fecha: "+dtoUsuarioMatricula.getFechaSancion().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    }

    @Test
    @DisplayName("Deberia Crear una matricula de manera correcta")
    void deberiaCrearLaMatriculaDeManeraCorrecta() {
        // arrange
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
        Mockito.when(repositorioMatricula.existePorId(Mockito.anyLong())).thenReturn(false);
        Mockito.when(daoUsuarioMatricula.listarPorId(Mockito.anyLong())).thenReturn(dtoUsuarioMatricula);
        Mockito.when(repositorioMatricula.crear(matricula, programa.getId(), usuario.getId())).thenReturn(10L);
        ServicioCrearMatricula servicioCrearMatricula = new ServicioCrearMatricula(repositorioMatricula, daoUsuarioMatricula);
        // act
        Long idMatricula = servicioCrearMatricula.ejecutar(matricula, programa.getId(), usuario.getId());
        //- assert
        assertEquals(10L,idMatricula);
        Mockito.verify(repositorioMatricula, Mockito.times(1)).crear(matricula, programa.getId(), usuario.getId());
    }
}
