package com.ceiba.matricula.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.matricula.comando.ComandoMatricula;
import com.ceiba.matricula.consulta.manejador.ManejadorActualizarMatricula;
import com.ceiba.matricula.consulta.manejador.ManejadorCrearMatricula;
import com.ceiba.matricula.consulta.manejador.ManejadorEliminarMatricula;
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

    @Autowired
    public ComandoControladorMatricula(ManejadorCrearMatricula manejadorCrearMatricula,
                                       ManejadorEliminarMatricula manejadorEliminarMatricula,
                                       ManejadorActualizarMatricula manejadorActualizarMatricula) {
        this.manejadorCrearMatricula = manejadorCrearMatricula;
		this.manejadorEliminarMatricula = manejadorEliminarMatricula;
		this.manejadorActualizarMatricula = manejadorActualizarMatricula;
    }

    @PostMapping
    @ApiOperation("Crear Matricula")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoMatricula comandoMatricula) {
        return manejadorCrearMatricula.ejecutar(comandoMatricula);
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
		manejadorActualizarMatricula.pagarMatricula(comandoMatricula);
	}
}
