package com.ceiba.matricula.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.matricula.comando.ComandoMatricula;
import com.ceiba.matricula.modelo.entidad.Programa;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;
import com.ceiba.matricula.servicio.testdatabuilder.ComandoMatriculaTestDataBuilder;
import com.ceiba.matricula.servicio.testdatabuilder.ProgramaTestDataBuilder;
import com.ceiba.matricula.servicio.testdatabuilder.UsuarioMatriculaTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorMatricula.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorMatriculaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    Programa programa;
    UsuarioMatricula usuario;

    @BeforeEach
    void setUp() {
        programa = new ProgramaTestDataBuilder().build();
        usuario = new UsuarioMatriculaTestDataBuilder().conId(null).build();
    }

    @Test
    @DisplayName("Deberia crear una matricula")
    void deberiaCrearUnaMatricula() throws Exception{
        // arrange
        ComandoMatricula matricula = new ComandoMatriculaTestDataBuilder(programa,usuario)
                .conId(null)
                .build();
        // act - assert
        mocMvc.perform(post("/matriculas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(matricula)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 1003}"));
    }

    @Test
    @DisplayName("Deberia eliminar una matricula")
    void deberiaEliminarUnaMatricula() throws Exception{
        // arrange
        // act - assert
        mocMvc.perform(delete("/matriculas/1000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deberia actualizar una matricula")
    void deberiaActualizarUnaMatricula() throws Exception{
        // arrange
        Long id = 1000L;
        ComandoMatricula matricula = new ComandoMatriculaTestDataBuilder(programa,usuario)
                .conId(id)
                .build();
        // act - assert
        mocMvc.perform(put("/matriculas/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(matricula)))
                .andExpect(status().isOk());
    }

}
