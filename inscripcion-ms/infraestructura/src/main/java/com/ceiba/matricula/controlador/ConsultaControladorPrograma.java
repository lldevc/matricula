package com.ceiba.matricula.controlador;

import com.ceiba.matricula.consulta.ManejadorListarProgramas;
import com.ceiba.matricula.modelo.dto.DtoPrograma;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/programas")
@Api(tags={"Controlador consulta los programas"})
public class ConsultaControladorPrograma {

    private final ManejadorListarProgramas manejadorListarPrograma;

    public ConsultaControladorPrograma(ManejadorListarProgramas manejadorListarPrograma) {
        this.manejadorListarPrograma = manejadorListarPrograma;
    }

    @GetMapping
    @ApiOperation("Listar programas")
    public List<DtoPrograma> listar() {
        return this.manejadorListarPrograma.ejecutar();
    }

}
