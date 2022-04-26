package com.ceiba.cron;

import com.ceiba.matricula.consulta.manejador.ManejadorActualizarMatricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronMatricula {

    private final ManejadorActualizarMatricula manejadorActualizarMatricula;

    @Autowired
    public CronMatricula(ManejadorActualizarMatricula manejadorActualizarMatricula) {
        this.manejadorActualizarMatricula = manejadorActualizarMatricula;
    }

    @Scheduled(cron = "0 * * * * *")
    public void cronActualizarEstadoDeMatriculas() {
        manejadorActualizarMatricula.actualizarEstadoMatriculas();
        System.out.println("=========================================");
        System.out.println("--------> Registros actualizados <-------");
        System.out.println("=========================================");
    }
}
