package com.ceiba.matricula.controlador;

import com.ceiba.ApplicationMock;
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
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorMatricula.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaControladorMatriculaTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deberia listar todas las matriculas")
    void deberiaListarMatriculas() throws Exception {

        mockMvc.perform(get("/matriculas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1000)))
                .andExpect(jsonPath("$[0].programa.id", is(1)))
                .andExpect(jsonPath("$[0].usuarioMatricula.id", is(1)))
                .andExpect(jsonPath("$[1].id", is(1001)))
                .andExpect(jsonPath("$[1].programa.id", is(2)))
                .andExpect(jsonPath("$[1].usuarioMatricula.id", is(2)))
                .andExpect(jsonPath("$[2].id", is(1002)))
                .andExpect(jsonPath("$[2].programa.id", is(1)))
                .andExpect(jsonPath("$[2].usuarioMatricula.id", is(3)));
    }

    @Test
    @DisplayName("Deberia listar la matricula por id")
    void deberiaListarMatriculaPorId() throws Exception {

        mockMvc.perform(get("/matriculas/1000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1000)))
                .andExpect(jsonPath("$.programa.id", is(1)))
                .andExpect(jsonPath("$.usuarioMatricula.id", is(1)))
                .andExpect(jsonPath("$.valor", is(700000D)));
    }

    @Test
    @DisplayName("Deberia listar la matriculas de un usuario por su id")
    void deberiaListarMatriculaPorIdDeUsuario() throws Exception {

        mockMvc.perform(get("/matriculas/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1000)))
                .andExpect(jsonPath("$[0].programa.id", is(1)))
                .andExpect(jsonPath("$[0].usuarioMatricula.id", is(1)))
                .andExpect(jsonPath("$[0].valor", is(700000D)));
    }

    @Test
    @DisplayName("Deberia listar la matriculas de un usuario por su numero de identificacion")
    void deberiaListarMatriculaPorNumeroDeIdentificacionUsuario() throws Exception {

        mockMvc.perform(get("/matriculas/usuarios/numero-identificacion/1111")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1000)))
                .andExpect(jsonPath("$[0].programa.id", is(1)))
                .andExpect(jsonPath("$[0].usuarioMatricula.id", is(1)))
                .andExpect(jsonPath("$[0].usuarioMatricula.numeroIdentificacion", is(1111)))
                .andExpect(jsonPath("$[0].valor", is(700000D)));
    }
}
