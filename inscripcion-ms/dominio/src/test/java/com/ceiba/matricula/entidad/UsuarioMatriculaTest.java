package com.ceiba.matricula.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;
import com.ceiba.matricula.servicio.testdatabuilder.UsuarioMatriculaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UsuarioMatriculaTest {

    @Test
    @DisplayName("Deberia crear correctamente el usuario")
    void deberiaCrearCorrectamenteElUsusuarioMatricula() {
        //act
        UsuarioMatricula usuario = new UsuarioMatriculaTestDataBuilder()
                .conId(1L)
                .conNumeroIdentificacion(1234L)
                .conNombre("Test")
                .conEmail("test@test.com")
                .build();
        //assert
        assertEquals(1, usuario.getId());
        assertEquals(1234, usuario.getNumeroIdentificacion());
        assertEquals("Test", usuario.getNombre());
        assertEquals("test@test.com", usuario.getEmail());
        assertEquals("1234", usuario.getCiudad());
        assertEquals("1234", usuario.getDireccion());
        assertNull(usuario.getFechaSancion());
    }

    @Test
    void deberiaFallarSinNombreDeUsuario() {

        //Arrange
        UsuarioMatriculaTestDataBuilder usuarioTestDataBuilder = new UsuarioMatriculaTestDataBuilder().conNombre(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el nombre de usuario");
    }

    @Test
    void deberiaFallarSinNumeroDeIdentificacion() {

        //Arrange
        UsuarioMatriculaTestDataBuilder usuarioTestDataBuilder = new UsuarioMatriculaTestDataBuilder().conNumeroIdentificacion(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el numero de identificaion de usuario");
    }

    @Test
    void deberiaFallarSinEmail() {

        //Arrange
        UsuarioMatriculaTestDataBuilder usuarioTestDataBuilder = new UsuarioMatriculaTestDataBuilder().conEmail(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el email de usuario");
    }

    @Test
    void deberiaFallarSiEmailEsInvalido() {

        //Arrange
        UsuarioMatriculaTestDataBuilder usuarioTestDataBuilder = new UsuarioMatriculaTestDataBuilder().conEmail("test-gmail.com");
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "El email ingresado es invalido");
    }


}
