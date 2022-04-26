package com.ceiba.matricula.consulta.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.matricula.servicio.ServicioEliminarMatricula;
import org.springframework.stereotype.Component;


@Component
public class ManejadorEliminarMatricula implements ManejadorComando<Long> {

    private final ServicioEliminarMatricula servicioEliminarMatricula;

    public ManejadorEliminarMatricula(ServicioEliminarMatricula servicioEliminarMatricula) {
        this.servicioEliminarMatricula = servicioEliminarMatricula;
    }

    public void ejecutar(Long idMatricula) {
        this.servicioEliminarMatricula.ejecutar(idMatricula);
    }
}
