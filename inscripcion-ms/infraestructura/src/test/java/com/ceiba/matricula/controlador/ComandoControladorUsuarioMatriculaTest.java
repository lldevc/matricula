package com.ceiba.matricula.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.matricula.comando.ComandoUsuarioMatricula;
import com.ceiba.matricula.servicio.testdatabuilder.ComandoUsuarioMatriculaTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorUsuarioMatricula.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorUsuarioMatriculaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia crear un usuario")
    void deberiaCrearUnUsuario() throws Exception{
        // arrange
        ComandoUsuarioMatricula usuario = new ComandoUsuarioMatriculaTestDataBuilder().conId(null).build();
        // act - assert
        mocMvc.perform(post("/usuarios-matricula")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 4}"));
    }

    @Test
    @DisplayName("Deberia actualizar un usuario")
    void deberiaActualizarUnUsuario() throws Exception{
        // arrange
        Long id = 1L;
        String nuevoTest = "test Update";
        String nuevoEmail = "test.update@test.com";
        ComandoUsuarioMatricula usuario = new ComandoUsuarioMatriculaTestDataBuilder().conId(id)
                .conNombre(nuevoTest)
                .conEmail(nuevoEmail)
                .conDireccion(nuevoTest)
                .conCiudad(nuevoTest)
                .build();
        // act - assert
        mocMvc.perform(put("/usuarios-matricula/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk());
    }

}
