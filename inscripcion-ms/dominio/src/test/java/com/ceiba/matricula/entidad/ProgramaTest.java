package com.ceiba.matricula.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.matricula.modelo.entidad.Programa;
import com.ceiba.matricula.servicio.testdatabuilder.ProgramaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProgramaTest {

    @Test
    @DisplayName("Deberia crear correctamente el programa con enfasis en ingles")
    void deberiaCrearCorrectamenteElProgramaIngles() {
        //act
        Programa programa = new ProgramaTestDataBuilder().ingles().build();
        //assert
        assertEquals(1111, programa.getId());
        assertEquals("Programa Ingles", programa.getNombre());
        assertEquals(0.2, programa.getRecargo());
        assertEquals(700000, programa.getPrecio());
        assertEquals(5, programa.getDiasParaRecargo());
    }

    @Test
    @DisplayName("Deberia crear correctamente el programa con enfasis en frances")
    void deberiaCrearCorrectamenteElProgramaFrances() {
        //act
        Programa programa = new ProgramaTestDataBuilder().frences().build();
        //assert
        assertEquals(1111, programa.getId());
        assertEquals("Programa Frances", programa.getNombre());
        assertEquals(0.2, programa.getRecargo());
        assertEquals(850000, programa.getPrecio());
        assertEquals(5, programa.getDiasParaRecargo());
    }

    @Test
    @DisplayName("Deberia crear correctamente el programa con enfasis en Aleman")
    void deberiaCrearCorrectamenteElProgramaAleman() {
        //act
        Programa programa = new ProgramaTestDataBuilder().aleman().build();
        //assert
        assertEquals(1111, programa.getId());
        assertEquals("Programa Aleman", programa.getNombre());
        assertEquals(0.2, programa.getRecargo());
        assertEquals(980000, programa.getPrecio());
        assertEquals(5, programa.getDiasParaRecargo());
    }

    @Test
    @DisplayName("Deberia calcular correctamente el recargo del programa")
    void deberiaCalcularCorrectamenteElRecargo() {
        //act
        Programa programa = new ProgramaTestDataBuilder().build();
        //assert
        assertEquals(20000D, programa.calcularRecargo());
    }

    @Test
    void deberiaFallarSinIdDePrograma() {

        //Arrange
        ProgramaTestDataBuilder programaTestDataBuilder = new ProgramaTestDataBuilder().conId(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    programaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar un id para el programa");
    }

    @Test
    void deberiaFallarSinNombreDePrograma() {

        //Arrange
        ProgramaTestDataBuilder programaTestDataBuilder = new ProgramaTestDataBuilder().conNombre(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    programaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar un nombre para el programa");
    }

    @Test
    void deberiaFallarSinPrecioDePrograma() {

        //Arrange
        ProgramaTestDataBuilder programaTestDataBuilder = new ProgramaTestDataBuilder().conPrecio(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    programaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar un precio para el programa");
    }

    @Test
    void deberiaFallarSinRecargoDePrograma() {

        //Arrange
        ProgramaTestDataBuilder programaTestDataBuilder = new ProgramaTestDataBuilder().conRecargo(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    programaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar un recargo para el programa");
    }

    @Test
    void deberiaFallarSinDiasParaRecargoDePrograma() {

        //Arrange
        ProgramaTestDataBuilder programaTestDataBuilder = new ProgramaTestDataBuilder().conDiasParaRecargo(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    programaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar los dias de plazo para el recargo del programa");
    }

    @Test
    void deberiaFallarSiRecargoNoEsPositivo() {

        //Arrange
        ProgramaTestDataBuilder programaTestDataBuilder = new ProgramaTestDataBuilder().conRecargo(-0.02);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    programaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "El valor del recargo debe ser positivo");
    }

    @Test
    void deberiaFallarSiPrecioNoEsPositivo() {

        //Arrange
        ProgramaTestDataBuilder programaTestDataBuilder = new ProgramaTestDataBuilder().conPrecio(-0.02);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    programaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "El valor del programa debe ser positivo");
    }

}
