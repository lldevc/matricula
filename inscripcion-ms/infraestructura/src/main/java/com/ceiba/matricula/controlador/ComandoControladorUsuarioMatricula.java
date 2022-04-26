package com.ceiba.matricula.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.matricula.comando.ComandoUsuarioMatricula;
import com.ceiba.matricula.consulta.manejador.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios-matricula")
@Api(tags = { "Controlador comando usuario de matricula"})
public class ComandoControladorUsuarioMatricula {

    private final ManejadorCrearUsuarioMatricula manejadorCrearUsuarioMatricula;
	private final ManejadorActualizarUsuarioMatricula manejadorActualizarUsuarioMatricula;

    @Autowired
    public ComandoControladorUsuarioMatricula(ManejadorCrearUsuarioMatricula manejadorCrearUsuarioMatricula,
											  ManejadorActualizarUsuarioMatricula manejadorActualizarUsuarioMatricula) {
        this.manejadorCrearUsuarioMatricula = manejadorCrearUsuarioMatricula;
		this.manejadorActualizarUsuarioMatricula = manejadorActualizarUsuarioMatricula;
    }

    @PostMapping
    @ApiOperation("Crear Usuario matricula")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoUsuarioMatricula comandoUsuarioMatricula) {
        return manejadorCrearUsuarioMatricula.ejecutar(comandoUsuarioMatricula);
    }

	@PutMapping(value="/{id}")
	@ApiOperation("Actualizar Usuario")
	public void actualizar(@RequestBody ComandoUsuarioMatricula comandoUsuarioMatricula,@PathVariable Long id) {
		comandoUsuarioMatricula.setId(id);
		manejadorActualizarUsuarioMatricula.ejecutar(comandoUsuarioMatricula);
	}
}
