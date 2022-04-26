package com.ceiba.matricula.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.matricula.modelo.entidad.TarjetaDeCredito;
import com.ceiba.matricula.servicio.testdatabuilder.TarjetaDeCredioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TarjetaDeCreditoTest {

    @Test
    @DisplayName("Deberia crear correctamente la tarjeta de credito")
    void deberiaCrearCorrectamenteLaTarjetaDeCredito() {
        //act
        TarjetaDeCredito tarjetaDeCredito = new TarjetaDeCredioTestDataBuilder().build();
        //assert
        assertEquals("1111111111111111", tarjetaDeCredito.getNumeroTarjeta());
        assertEquals("3022", tarjetaDeCredito.getAnioVecimiento());
        assertEquals("01", tarjetaDeCredito.getMesVecimiento());
        assertEquals("123", tarjetaDeCredito.getCodigoSeguridad());
    }

    @Test
    void deberiaFallarSinNumeroDeTarjeta() {

        //Arrange
        TarjetaDeCredioTestDataBuilder tarjetaDeCredito = new TarjetaDeCredioTestDataBuilder().conNumeroTarjeta(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    tarjetaDeCredito.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar un numero de targeta");
    }

    @Test
    void deberiaFallarSinAnioDeVencimiento() {

        //Arrange
        TarjetaDeCredioTestDataBuilder tarjetaDeCredito = new TarjetaDeCredioTestDataBuilder().conAnioVecimiento(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    tarjetaDeCredito.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar un año de vencimiento");
    }


    @Test
    void deberiaFallarSinMesDeVenvimiento() {

        //Arrange
        TarjetaDeCredioTestDataBuilder tarjetaDeCredito = new TarjetaDeCredioTestDataBuilder().conMesVecimiento(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    tarjetaDeCredito.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar un mes de vencimiento");
    }


    @Test
    void deberiaFallarSinCodigoDeSeguridad() {

        //Arrange
        TarjetaDeCredioTestDataBuilder tarjetaDeCredito = new TarjetaDeCredioTestDataBuilder().conCodigoSeguridad(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    tarjetaDeCredito.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar un codigo de seguridad");
    }

    @Test
    void deberiaFallarConNumeroDeTarjetaInvalido() {

        //Arrange
        TarjetaDeCredioTestDataBuilder tarjetaDeCredito = new TarjetaDeCredioTestDataBuilder().conNumeroTarjeta("1");
        //act-assert
        BasePrueba.assertThrows(() -> {
                    tarjetaDeCredito.build();
                },
                ExcepcionValorInvalido.class, "El numero de la tageta ingresado es invalido");
    }

    @Test
    void deberiaFallarConAnioDeVencimientoInvalido() {

        //Arrange
        TarjetaDeCredioTestDataBuilder tarjetaDeCredito = new TarjetaDeCredioTestDataBuilder().conAnioVecimiento("1");
        //act-assert
        BasePrueba.assertThrows(() -> {
                    tarjetaDeCredito.build();
                },
                ExcepcionValorInvalido.class, "El año ingresado es invalido");
    }


    @Test
    void deberiaFallarConMesDeVenvimientoInvalido() {

        //Arrange
        TarjetaDeCredioTestDataBuilder tarjetaDeCredito = new TarjetaDeCredioTestDataBuilder().conMesVecimiento("1");
        //act-assert
        BasePrueba.assertThrows(() -> {
                    tarjetaDeCredito.build();
                },
                ExcepcionValorInvalido.class, "El mes ingresaso es invalido");
    }


    @Test
    void deberiaFallarConCodigoDeSeguridadInvalido() {

        //Arrange
        TarjetaDeCredioTestDataBuilder tarjetaDeCredito = new TarjetaDeCredioTestDataBuilder().conCodigoSeguridad("1");
        //act-assert
        BasePrueba.assertThrows(() -> {
                    tarjetaDeCredito.build();
                },
                ExcepcionValorInvalido.class, "El codigo de seguridad ingresado es invalido");
    }


}
