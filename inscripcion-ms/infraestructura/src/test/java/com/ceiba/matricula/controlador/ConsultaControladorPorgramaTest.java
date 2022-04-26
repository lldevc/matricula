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
@WebMvcTest(ConsultaControladorPrograma.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaControladorPorgramaTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deberia listar los programas")
    void deberiaListarProgramas() throws Exception {

        mockMvc.perform(get("/programas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nombre", is("Ingles")))
                .andExpect(jsonPath("$[0].precio", is(700000D)))
                .andExpect(jsonPath("$[0].recargo", is(0.2)))
                .andExpect(jsonPath("$[0].diasParaRecargo", is(5)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].nombre", is("Frances")))
                .andExpect(jsonPath("$[1].precio", is(850000D)))
                .andExpect(jsonPath("$[1].recargo", is(0.2)))
                .andExpect(jsonPath("$[1].diasParaRecargo", is(7)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].nombre", is("Aleman")))
                .andExpect(jsonPath("$[2].precio", is(980000D)))
                .andExpect(jsonPath("$[2].recargo", is(0.2)))
                .andExpect(jsonPath("$[2].diasParaRecargo", is(9)));
    }
}
