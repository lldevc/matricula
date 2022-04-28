package com.ceiba.matricula.cron;

import com.ceiba.matricula.consulta.manejador.ManejadorActualizarEstadoMatricula;
import com.ceiba.matricula.consulta.manejador.ManejadorActualizarMatricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronMatricula {

    private final ManejadorActualizarEstadoMatricula manejadorActualizarEstadoMatricula;

    @Autowired
    public CronMatricula(ManejadorActualizarEstadoMatricula manejadorActualizarEstadoMatricula) {
        this.manejadorActualizarEstadoMatricula = manejadorActualizarEstadoMatricula;
    }

    @Scheduled(cron = "0 * * * * *")
    public void cronActualizarEstadoDeMatriculas() {
        manejadorActualizarEstadoMatricula.ejecutar();
        System.out.println("=========================================");
        System.out.println("--------> Registros actualizados <-------");
        System.out.println("=========================================");
    }
}
