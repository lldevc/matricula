package com.ceiba.matricula.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.matricula.modelo.entidad.EstadoDePago;
import com.ceiba.matricula.modelo.entidad.Matricula;
import com.ceiba.matricula.modelo.entidad.Programa;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;
import com.ceiba.matricula.servicio.testdatabuilder.MatriculaTestDataBuilder;
import com.ceiba.matricula.servicio.testdatabuilder.ProgramaTestDataBuilder;
import com.ceiba.matricula.servicio.testdatabuilder.UsuarioMatriculaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class MatriculaTest {

    @Test
    @DisplayName("Deberia crear correctamente la matricula")
    void deberiaCrearCorrectamenteLaMatricula() {
        // arrange
        Programa programa = new ProgramaTestDataBuilder().build();
        UsuarioMatricula usuario = new UsuarioMatriculaTestDataBuilder().build();
        LocalDateTime fechaCreacion = LocalDateTime.now();
        LocalDateTime fechaLimitePagoConRecargo = MatriculaTestDataBuilder.calcularFechaLimitePago(fechaCreacion, programa.getDiasParaRecargo());
        LocalDateTime fechaMaximaPago = MatriculaTestDataBuilder.calcularFechaLimitePago(fechaCreacion, programa.getDiasParaRecargo() + 5);
        //act
        Matricula matricula = new MatriculaTestDataBuilder(programa, usuario)
                .conFechaCreacion(fechaCreacion)
                .conFechaLimitePagoConRecargo(fechaLimitePagoConRecargo)
                .conFechaMaximaPago(fechaMaximaPago)
                .build();
        //assert
        assertEquals(1234, matricula.getId());
        assertEquals(programa.getPrecio(), matricula.getValor());
        assertFalse(matricula.isRecargo());
        assertEquals(EstadoDePago.PENDIENTE, matricula.getEstadoDePago());
        assertEquals(fechaCreacion, matricula.getFechaCreacion());
        assertEquals(fechaLimitePagoConRecargo, matricula.getFechaLimitePagoSinRecargo());
        assertEquals(fechaMaximaPago, matricula.getFechaMaximaPago());
        assertEquals(usuario, matricula.getUsuarioMatricula());
        assertEquals(programa, matricula.getPrograma());
    }

    @Test
    @DisplayName("Deberia crear correctamente la matricula con contructor sencillo")
    void deberiaCrearCorrectamenteLaMatriculaConElConstructorSencillo() {
        // arrange
        Programa programa = new ProgramaTestDataBuilder().build();
        UsuarioMatricula usuario = new UsuarioMatriculaTestDataBuilder().build();
        LocalDateTime fechaCreacion = LocalDateTime.now();
        LocalDateTime fechaLimitePagoConRecargo = MatriculaTestDataBuilder.calcularFechaLimitePago(fechaCreacion, programa.getDiasParaRecargo());
        LocalDateTime fechaMaximaPago = MatriculaTestDataBuilder.calcularFechaLimitePago(fechaCreacion, programa.getDiasParaRecargo() + 5);
        //act

        Matricula matricula = new MatriculaTestDataBuilder(programa, usuario)
                .conFechaCreacion(fechaCreacion)
                .conFechaLimitePagoConRecargo(fechaLimitePagoConRecargo)
                .conFechaMaximaPago(fechaMaximaPago)
                .build2();

        //assert
        assertNull(matricula.getId());
        assertEquals(programa.getPrecio(), matricula.getValor());
        assertFalse(matricula.isRecargo());
        assertEquals(EstadoDePago.PENDIENTE, matricula.getEstadoDePago());
        assertEquals(fechaLimitePagoConRecargo, matricula.getFechaLimitePagoSinRecargo());
        assertEquals(fechaMaximaPago, matricula.getFechaMaximaPago());
        assertEquals(usuario, matricula.getUsuarioMatricula());
        assertEquals(programa, matricula.getPrograma());
        assertTrue(matricula.getFechaLimitePagoSinRecargo().isBefore(matricula.getFechaMaximaPago()));
    }

    @Test
    @DisplayName("Deberia crear correctamente la matricula con las fechas correctas pagando en dia domingo")
    void deberiaCrearCorrectamenteLaMatriculaConLasFechasCorrectasConPagoDiaDomingo() {
        // arrange
        Programa programa = new ProgramaTestDataBuilder().build();
        UsuarioMatricula usuario = new UsuarioMatriculaTestDataBuilder().build();
        LocalDateTime fechaCreacion = LocalDateTime.of(2022, 4, 3, 8,0);
        LocalDateTime fechaLimitePagoConRecargo = MatriculaTestDataBuilder.calcularFechaLimitePago(fechaCreacion, programa.getDiasParaRecargo());
        LocalDateTime fechaMaximaPago = MatriculaTestDataBuilder.calcularFechaLimitePago(fechaCreacion, programa.getDiasParaRecargo() + 5);
        LocalDateTime fechaLimitePagoSinRecargoEsperada = LocalDateTime.of(2022, 4, 8, 23,59, 59);
        LocalDateTime fechaMaximaPagoEsperada = LocalDateTime.of(2022, 4, 15, 23,59, 59);
        //act
        Matricula matricula = new MatriculaTestDataBuilder(programa, usuario)
                .conFechaCreacion(fechaCreacion)
                .conFechaLimitePagoConRecargo(fechaLimitePagoConRecargo)
                .conFechaMaximaPago(fechaMaximaPago)
                .build();
        //assert
        assertEquals(1234, matricula.getId());
        assertEquals(programa.getPrecio(), matricula.getValor());
        assertFalse(matricula.isRecargo());
        assertEquals(EstadoDePago.PENDIENTE, matricula.getEstadoDePago());
        assertEquals(fechaCreacion, matricula.getFechaCreacion());
        assertEquals(fechaLimitePagoSinRecargoEsperada, matricula.getFechaLimitePagoSinRecargo());
        assertEquals(fechaMaximaPagoEsperada, matricula.getFechaMaximaPago());
        assertEquals(usuario, matricula.getUsuarioMatricula());
        assertEquals(programa, matricula.getPrograma());
    }

    @Test
    @DisplayName("Deberia crear correctamente la matricula con las fechas correctas pagando en dia sabado")
    void deberiaCrearCorrectamenteLaMatriculaConLasFechasCorrectasConPagoDiaSabado() {
        // arrange
        Programa programa = new ProgramaTestDataBuilder().build();
        UsuarioMatricula usuario = new UsuarioMatriculaTestDataBuilder().build();
        LocalDateTime fechaCreacion = LocalDateTime.of(2022, 4, 2, 8,0);
        LocalDateTime fechaLimitePagoConRecargo = MatriculaTestDataBuilder.calcularFechaLimitePago(fechaCreacion, programa.getDiasParaRecargo());
        LocalDateTime fechaMaximaPago = MatriculaTestDataBuilder.calcularFechaLimitePago(fechaCreacion, programa.getDiasParaRecargo() + 5);
        LocalDateTime fechaLimitePagoSinRecargoEsperada = LocalDateTime.of(2022, 4, 8, 23,59, 59);
        LocalDateTime fechaMaximaPagoEsperada = LocalDateTime.of(2022, 4, 15, 23,59, 59);
        //act
        Matricula matricula = new MatriculaTestDataBuilder(programa, usuario)
                .conFechaCreacion(fechaCreacion)
                .conFechaLimitePagoConRecargo(fechaLimitePagoConRecargo)
                .conFechaMaximaPago(fechaMaximaPago)
                .build();
        //assert
        assertEquals(1234, matricula.getId());
        assertEquals(programa.getPrecio(), matricula.getValor());
        assertFalse(matricula.isRecargo());
        assertEquals(EstadoDePago.PENDIENTE, matricula.getEstadoDePago());
        assertEquals(fechaCreacion, matricula.getFechaCreacion());
        assertEquals(fechaLimitePagoSinRecargoEsperada, matricula.getFechaLimitePagoSinRecargo());
        assertEquals(fechaMaximaPagoEsperada, matricula.getFechaMaximaPago());
        assertEquals(usuario, matricula.getUsuarioMatricula());
        assertEquals(programa, matricula.getPrograma());
    }

    @Test
    @DisplayName("Deberia crear correctamente la matricula con las fechas correctas pagando en dia viernes")
    void deberiaCrearCorrectamenteLaMatriculaConLasFechasCorrectasConPagoDiaViernes() {
        // arrange
        Programa programa = new ProgramaTestDataBuilder().build();
        UsuarioMatricula usuario = new UsuarioMatriculaTestDataBuilder().build();
        LocalDateTime fechaCreacion = LocalDateTime.of(2022, 4, 1, 8,0);
        LocalDateTime fechaLimitePagoConRecargo = MatriculaTestDataBuilder.calcularFechaLimitePago(fechaCreacion, programa.getDiasParaRecargo());
        LocalDateTime fechaMaximaPago = MatriculaTestDataBuilder.calcularFechaLimitePago(fechaCreacion, programa.getDiasParaRecargo() + 5);
        LocalDateTime fechaLimitePagoSinRecargoEsperada = LocalDateTime.of(2022, 4, 8, 23,59, 59);
        LocalDateTime fechaMaximaPagoEsperada = LocalDateTime.of(2022, 4, 15, 23,59, 59);
        //act
        Matricula matricula = new MatriculaTestDataBuilder(programa, usuario)
                .conFechaCreacion(fechaCreacion)
                .conFechaLimitePagoConRecargo(fechaLimitePagoConRecargo)
                .conFechaMaximaPago(fechaMaximaPago)
                .build();
        //assert
        assertEquals(1234, matricula.getId());
        assertEquals(programa.getPrecio(), matricula.getValor());
        assertFalse(matricula.isRecargo());
        assertEquals(EstadoDePago.PENDIENTE, matricula.getEstadoDePago());
        assertEquals(fechaCreacion, matricula.getFechaCreacion());
        assertEquals(fechaLimitePagoSinRecargoEsperada, matricula.getFechaLimitePagoSinRecargo());
        assertEquals(fechaMaximaPagoEsperada, matricula.getFechaMaximaPago());
        assertEquals(usuario, matricula.getUsuarioMatricula());
        assertEquals(programa, matricula.getPrograma());
    }

    @Test
    void deberiaFallarSinId() {

        //Arrange
        Programa programa = new ProgramaTestDataBuilder().build();
        UsuarioMatricula usuario = new UsuarioMatriculaTestDataBuilder().build();
        MatriculaTestDataBuilder matriculaTestDataBuilder = new MatriculaTestDataBuilder(programa, usuario).conId(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    matriculaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "La matricula debe tener un id");
    }

    @Test
    void deberiaFallarSinPrograma() {

        //Arrange
        Programa programa = new ProgramaTestDataBuilder().build();
        UsuarioMatricula usuario = new UsuarioMatriculaTestDataBuilder().build();
        MatriculaTestDataBuilder matriculaTestDataBuilder = new MatriculaTestDataBuilder(programa, usuario).conPrograma(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    matriculaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "La matricula debe tener un programa");
    }

    @Test
    void deberiaFallarSinUsuarioMatricula() {

        //Arrange
        Programa programa = new ProgramaTestDataBuilder().build();
        UsuarioMatricula usuario = new UsuarioMatriculaTestDataBuilder().build();
        MatriculaTestDataBuilder matriculaTestDataBuilder = new MatriculaTestDataBuilder(programa, usuario).conUsuarioMatricula(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    matriculaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "La matricula debe tener un estudiante");
    }

    @Test
    void deberiaFallarSinValor() {

        //Arrange
        Programa programa = new ProgramaTestDataBuilder().build();
        UsuarioMatricula usuario = new UsuarioMatriculaTestDataBuilder().build();
        MatriculaTestDataBuilder matriculaTestDataBuilder = new MatriculaTestDataBuilder(programa, usuario).conValor(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    matriculaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "La matricula debe tener un valor");
    }

    @Test
    void deberiaFallarSinEstadoDePago() {

        //Arrange
        Programa programa = new ProgramaTestDataBuilder().build();
        UsuarioMatricula usuario = new UsuarioMatriculaTestDataBuilder().build();
        MatriculaTestDataBuilder matriculaTestDataBuilder = new MatriculaTestDataBuilder(programa, usuario).conEstadoDePago(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    matriculaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el estado de la matricula");
    }

    @Test
    void deberiaFallarSinFechaDeCreacion() {

        //Arrange
        Programa programa = new ProgramaTestDataBuilder().build();
        UsuarioMatricula usuario = new UsuarioMatriculaTestDataBuilder().build();
        MatriculaTestDataBuilder matriculaTestDataBuilder = new MatriculaTestDataBuilder(programa, usuario).conFechaCreacion(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    matriculaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de creación");
    }

    @Test
    void deberiaFallarSinFechaDePagoParaRecargo() {

        //Arrange
        Programa programa = new ProgramaTestDataBuilder().build();
        UsuarioMatricula usuario = new UsuarioMatriculaTestDataBuilder().build();
        MatriculaTestDataBuilder matriculaTestDataBuilder = new MatriculaTestDataBuilder(programa, usuario).conFechaLimitePagoConRecargo(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    matriculaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de pago con recargo");
    }

    @Test
    void deberiaFallarSinFechaDePagoMaximo() {

        //Arrange
        Programa programa = new ProgramaTestDataBuilder().build();
        UsuarioMatricula usuario = new UsuarioMatriculaTestDataBuilder().build();
        MatriculaTestDataBuilder matriculaTestDataBuilder = new MatriculaTestDataBuilder(programa, usuario).conFechaMaximaPago(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    matriculaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de pago maxima");
    }

    @Test
    void deberiaFallarConValorNegativo() {

        //Arrange
        Programa programa = new ProgramaTestDataBuilder().build();
        UsuarioMatricula usuario = new UsuarioMatriculaTestDataBuilder().build();
        MatriculaTestDataBuilder matriculaTestDataBuilder = new MatriculaTestDataBuilder(programa, usuario).conValor(-100000D);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    matriculaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "La matricula debe tener un valor positivo");
    }

    @Test
    void deberiaFallarAlSetearValorNegativo() {

        //Arrange
        Programa programa = new ProgramaTestDataBuilder().build();
        UsuarioMatricula usuario = new UsuarioMatriculaTestDataBuilder().build();
        MatriculaTestDataBuilder matriculaTestDataBuilder = new MatriculaTestDataBuilder(programa, usuario);
        //act-assert
        BasePrueba.assertThrows(() -> matriculaTestDataBuilder.build().setValor(-100000D),
                ExcepcionValorInvalido.class, "La matricula debe tener un valor positivo");
    }

    @Test
    void deberiaCalcularCorrectamenteLaFechaDePago() {
        // arrange
        LocalDateTime viernes = LocalDateTime.of(2022, 4, 1, 8,0, 0);
        LocalDateTime sabado = LocalDateTime.of(2022, 4, 2, 8,0, 0);
        LocalDateTime domingo = LocalDateTime.of(2022, 4, 3, 8,0, 0);
        LocalDateTime lunes = LocalDateTime.of(2022, 4, 4, 8,0, 0);
        LocalDateTime fechaEsperadaConPagoDiaViernes = LocalDateTime.of(2022, 4, 8, 23,59, 59);
        LocalDateTime fechaEsperadaConPagoDiaDomingo = LocalDateTime.of(2022, 4, 8, 23,59, 59);
        LocalDateTime fechaEsperadaConPagoDiaSabado = LocalDateTime.of(2022, 4, 8, 23,59, 59);
        LocalDateTime fechaEsperadaConPagoDiaLunes = LocalDateTime.of(2022, 4, 11, 23,59, 59);
        int diasParaPago = 5;
        //act
        LocalDateTime fechaPagoViernes = Matricula.calcularFechaDePago(viernes, diasParaPago);
        LocalDateTime fechaPagoSabado = Matricula.calcularFechaDePago(sabado, diasParaPago);
        LocalDateTime fechaPagoDomingo = Matricula.calcularFechaDePago(domingo, diasParaPago);
        LocalDateTime fechaPagoLunes = Matricula.calcularFechaDePago(lunes, diasParaPago);
        //assert
        assertEquals(fechaEsperadaConPagoDiaViernes, fechaPagoViernes);
        assertEquals(fechaEsperadaConPagoDiaDomingo, fechaPagoSabado);
        assertEquals(fechaEsperadaConPagoDiaSabado, fechaPagoDomingo);
        assertEquals(fechaEsperadaConPagoDiaLunes, fechaPagoLunes);
    }


}
