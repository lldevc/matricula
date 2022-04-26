package com.ceiba.matricula.controlador;

import com.ceiba.matricula.consulta.ManejadorListarMatricula;
import com.ceiba.matricula.modelo.dto.DtoMatricula;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/matriculas")
@Api(tags={"Controlador consulta matricula"})
public class ConsultaControladorMatricula {

    private final ManejadorListarMatricula manejadorListarMatricula;

    public ConsultaControladorMatricula(ManejadorListarMatricula manejadorListarMatricula) {
        this.manejadorListarMatricula = manejadorListarMatricula;
    }

    @GetMapping
    @ApiOperation("Listar Matriculas")
    public List<DtoMatricula> listar() {
        return this.manejadorListarMatricula.ejecutar();
    }

    @GetMapping("/{id}")
    @ApiOperation("Listar Matricula por id")
    public DtoMatricula listarPorId(@PathVariable Long id) {
        return this.manejadorListarMatricula.ejecutarListarPorId(id);
    }

    @GetMapping("/usuarios/{id}")
    @ApiOperation("Listar Matriculas por id de usuario")
    public List<DtoMatricula> listarPorIdUsuario(@PathVariable Long id) {
        return this.manejadorListarMatricula.ejecutarListarPorIdUsuario(id);
    }

    @GetMapping("/usuarios/numero-identificacion/{numeroIdentificacion}")
    @ApiOperation("Listar Matriculas por id de usuario")
    public List<DtoMatricula> listarPorNumeroIdentificacionUsuario(@PathVariable Long numeroIdentificacion) {
        return this.manejadorListarMatricula.ejecutarListarPorNumeroIdentificacionUsuario(numeroIdentificacion);
    }

}
