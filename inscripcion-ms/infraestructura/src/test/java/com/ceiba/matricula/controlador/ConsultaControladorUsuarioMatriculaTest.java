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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorUsuarioMatricula.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorUsuarioMatriculaTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia listar usuarios")
    void deberiaListarUsuarios() throws Exception {
        // arrange
        // act - assert
        mocMvc.perform(get("/usuarios-matricula")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].numeroIdentificacion", is(1111)))
                .andExpect(jsonPath("$[0].nombre", is("test")))
                .andExpect(jsonPath("$[0].email", is("test@test.com")))
                .andExpect(jsonPath("$[0].ciudad", is("testCiudad")))
                .andExpect(jsonPath("$[0].direccion", is("testDireccion")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].numeroIdentificacion", is(1112)))
                .andExpect(jsonPath("$[1].nombre", is("test2")))
                .andExpect(jsonPath("$[1].email", is("test2@test.com")))
                .andExpect(jsonPath("$[1].ciudad", is("testCiudad2")))
                .andExpect(jsonPath("$[1].direccion", is("testDireccion2")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].numeroIdentificacion", is(1113)))
                .andExpect(jsonPath("$[2].nombre", is("test3")))
                .andExpect(jsonPath("$[2].email", is("test3@test.com")))
                .andExpect(jsonPath("$[2].ciudad", is("testCiudad3")))
                .andExpect(jsonPath("$[2].direccion", is("testDireccion3")));

    }

    @Test
    @DisplayName("Deberia listar usuario por id")
    void deberiaListarUsuariosPorId() throws Exception {
        // arrange
        String fechaSacion = LocalDateTime.of(2022,10,18,0,0,0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // act - assert
        mocMvc.perform(get("/usuarios-matricula/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.numeroIdentificacion", is(1111)))
                .andExpect(jsonPath("$.nombre", is("test")))
                .andExpect(jsonPath("$.email", is("test@test.com")))
                .andExpect(jsonPath("$.ciudad", is("testCiudad")))
                .andExpect(jsonPath("$.direccion", is("testDireccion")))
                .andExpect(jsonPath("$.fechaSancion", is(fechaSacion)));

    }


}
