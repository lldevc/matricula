package com.ceiba.matricula.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.matricula.comando.ComandoCrearMatricula;
import com.ceiba.matricula.comando.ComandoMatricula;
import com.ceiba.matricula.consulta.manejador.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculas")
@Api(tags = { "Controlador comando matricula"})
public class ComandoControladorMatricula {

    private final ManejadorCrearMatricula manejadorCrearMatricula;
	private final ManejadorEliminarMatricula manejadorEliminarMatricula;
	private final ManejadorActualizarMatricula manejadorActualizarMatricula;
	private final ManejadorPagarMatricula manejadorPagarMatricula;
	private final ManejadorActualizarEstadoMatricula manejadorActualizarEstadoMatricula;

    @Autowired
    public ComandoControladorMatricula(ManejadorCrearMatricula manejadorCrearMatricula,
									   ManejadorEliminarMatricula manejadorEliminarMatricula,
									   ManejadorActualizarMatricula manejadorActualizarMatricula,
									   ManejadorPagarMatricula manejadorPagarMatricula,
									   ManejadorActualizarEstadoMatricula manejadorActualizarEstadoMatricula) {
        this.manejadorCrearMatricula = manejadorCrearMatricula;
		this.manejadorEliminarMatricula = manejadorEliminarMatricula;
		this.manejadorActualizarMatricula = manejadorActualizarMatricula;
		this.manejadorPagarMatricula = manejadorPagarMatricula;
		this.manejadorActualizarEstadoMatricula = manejadorActualizarEstadoMatricula;
	}

    @PostMapping
    @ApiOperation("Crear Matricula")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoCrearMatricula comandoCrearMatricula) {
        return manejadorCrearMatricula.ejecutar(comandoCrearMatricula);
    }

    @DeleteMapping(value="/{id}")
	@ApiOperation("Eliminar Matricula")
	public void eliminar(@PathVariable Long id) {
		manejadorEliminarMatricula.ejecutar(id);
	}

	@PutMapping(value="/{id}")
	@ApiOperation("Actualizar Matricula")
	public void actualizar(@RequestBody ComandoMatricula comandoMatricula,@PathVariable Long id) {
		comandoMatricula.setId(id);
		manejadorActualizarMatricula.ejecutar(comandoMatricula);
	}

	@PutMapping(value="/pagar/{id}")
	@ApiOperation("Actualizar el estado de pago de la Matricula")
	public void actualizarPagoMatricula(@RequestBody ComandoMatricula comandoMatricula,@PathVariable Long id) {
		comandoMatricula.setId(id);
		manejadorPagarMatricula.ejecutar(comandoMatricula);
	}

	@PutMapping(value="/cron")
	@ApiOperation("Actualizar el estado de pago de la Matricula")
	public void cronMatricula() {
		manejadorActualizarEstadoMatricula.ejecutar();
	}
}
