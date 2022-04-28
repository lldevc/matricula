package com.ceiba.matricula.consulta.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.matricula.comando.ComandoMatricula;
import com.ceiba.matricula.comando.fabrica.FabricaMatricula;
import com.ceiba.matricula.modelo.entidad.Matricula;
import com.ceiba.matricula.servicio.ServicioActualizarMatricula;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarMatricula implements ManejadorComando<ComandoMatricula> {

    private final FabricaMatricula fabricaMatricula;
    private final ServicioActualizarMatricula servicioActualizarMatricula;

    public ManejadorActualizarMatricula(FabricaMatricula fabricaMatricula, ServicioActualizarMatricula servicioActualizarMatricula) {
        this.fabricaMatricula = fabricaMatricula;
        this.servicioActualizarMatricula = servicioActualizarMatricula;
    }

    public void ejecutar(ComandoMatricula comandoMatricula) {
        Matricula matricula = this.fabricaMatricula.crear(comandoMatricula);
        this.servicioActualizarMatricula.ejecutar(matricula);
    }
}
