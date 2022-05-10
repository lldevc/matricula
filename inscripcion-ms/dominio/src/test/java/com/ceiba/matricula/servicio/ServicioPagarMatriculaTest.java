package com.ceiba.matricula.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.matricula.modelo.entidad.*;
import com.ceiba.matricula.puerto.repositorio.RepositorioMatricula;
import com.ceiba.matricula.servicio.testdatabuilder.MatriculaTestDataBuilder;
import com.ceiba.matricula.servicio.testdatabuilder.ProgramaTestDataBuilder;
import com.ceiba.matricula.servicio.testdatabuilder.TarjetaDeCredioTestDataBuilder;
import com.ceiba.matricula.servicio.testdatabuilder.UsuarioMatriculaTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static com.ceiba.matricula.modelo.entidad.EstadoDePago.PAGADA;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioPagarMatriculaTest {

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
        ServicioPagarMatricula servicioPagarMatricula = new ServicioPagarMatricula(repositorioMatricula);
        // act - assert
        BasePrueba.assertThrows(() -> servicioPagarMatricula.ejecutar(matricula, tarjetaDeCredito), ExcepcionDuplicidad.class,"La matricula no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia validar que la matricula esta vencida")
    void deberiaValidarQueLaMatriculaEstaVencida() {
        // arrange
        Matricula matriculaVencida = new MatriculaTestDataBuilder(programa, usuario).conId(1L).conEstadoDePago(EstadoDePago.VENCIDA).build();
        Mockito.when(repositorioMatricula.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioPagarMatricula servicioPagarMatricula = new ServicioPagarMatricula(repositorioMatricula);
        // act - assert
        BasePrueba.assertThrows(() -> servicioPagarMatricula.ejecutar(matriculaVencida, tarjetaDeCredito), ExcepcionDuplicidad.class,"La matricula esta vencida");
    }

    @Test
    @DisplayName("Deberia validar que la tarjeta de credito esta vencida")
    void deberiaValidarQueLaTarjetaDeCreditoEstaVencida() {
        // arrange
        String anioTarjetaVencida = "2000";
        TarjetaDeCredito tarjetaDeCreditoVencida = new TarjetaDeCredioTestDataBuilder().conAnioVecimiento(anioTarjetaVencida).build();
        Mockito.when(repositorioMatricula.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioPagarMatricula servicioPagarMatricula = new ServicioPagarMatricula(repositorioMatricula);
        // act - //assert
        BasePrueba.assertThrows(() -> servicioPagarMatricula.ejecutar(matricula, tarjetaDeCreditoVencida), ExcepcionValorInvalido.class,"La tarjeta ingreasada para el pago de la matricula está vencida");
    }

    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio")
    void deberiaActualizarCorrectamenteElRepositorio() {
        // arrange
        Mockito.when(repositorioMatricula.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioPagarMatricula servicioPagarMatricula = new ServicioPagarMatricula(repositorioMatricula);
        ArgumentCaptor<Matricula> matriculaArgumentCaptor = ArgumentCaptor.forClass(Matricula.class);
        // act
        servicioPagarMatricula.ejecutar(matricula, tarjetaDeCredito);
        //assert
        Mockito.verify(repositorioMatricula).actualizar(matriculaArgumentCaptor.capture());
        Mockito.verify(repositorioMatricula,Mockito.times(1)).actualizar(matricula);
        Matricula matriculaActualizada = matriculaArgumentCaptor.getValue();
        assertEquals(PAGADA, matriculaActualizada.getEstadoDePago());
    }
}
