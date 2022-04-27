package com.ceiba.matricula.entidad;

import com.ceiba.matricula.modelo.dto.DtoPrograma;
import com.ceiba.matricula.servicio.testdatabuilder.DtoProgramaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DtoProgramaTest {

    @Test
    @DisplayName("Deberia crear correctamente el programa con enfasis en ingles")
    void deberiaCrearCorrectamenteElProgramaIngles() {
        //act
        DtoPrograma programa = new DtoProgramaTestDataBuilder().build();
        //assert
        assertEquals(1111, programa.getId());
        assertEquals("Programa Test", programa.getNombre());
        assertEquals(0.2, programa.getRecargo());
        assertEquals(100000, programa.getPrecio());
        assertEquals(5, programa.getDiasParaRecargo());
    }


}
