package com.ceiba.matricula.controlador;

import com.ceiba.matricula.consulta.ManejadorListarUsuariosMatricula;
import com.ceiba.matricula.modelo.dto.DtoUsuarioMatricula;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios-matricula")
@Api(tags={"Controlador consulta usuarios de matricula"})
public class ConsultaControladorUsuarioMatricula {

    private final ManejadorListarUsuariosMatricula manejadorListarUsuariosMatricula;

    public ConsultaControladorUsuarioMatricula(ManejadorListarUsuariosMatricula manejadorListarUsuariosMatricula) {
        this.manejadorListarUsuariosMatricula = manejadorListarUsuariosMatricula;
    }

    @GetMapping
    @ApiOperation("Listar Usuarios")
    public List<DtoUsuarioMatricula> listar() {
        return this.manejadorListarUsuariosMatricula.ejecutar();
    }

    @GetMapping("/{id}")
    @ApiOperation("Listar Usuarios")
    public DtoUsuarioMatricula listar(@PathVariable Long id) {
        return this.manejadorListarUsuariosMatricula.ejecutar(id);
    }

}
