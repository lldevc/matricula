package com.ceiba.matricula.cron;

import com.ceiba.matricula.consulta.manejador.ManejadorActualizarEstadoMatricula;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class CronMatricula {

    private static final Logger LOGGER = LoggerFactory.getLogger(CronMatricula.class);
    private static final String INICIO_ACTUALIZACION = "Inicio de actualizacion estados de matriculas";
    private static final String FINALIZACION_ACTUALIZACION = "Finalizacion actualizacion estados de matricula";

    private final ManejadorActualizarEstadoMatricula manejadorActualizarEstadoMatricula;

    @Autowired
    public CronMatricula(ManejadorActualizarEstadoMatricula manejadorActualizarEstadoMatricula) {
        this.manejadorActualizarEstadoMatricula = manejadorActualizarEstadoMatricula;
    }

    @Scheduled(cron = "${cron.expression.matricula}")
    public void cronActualizarEstadoDeMatriculas(){
        LOGGER.info(INICIO_ACTUALIZACION);
        manejadorActualizarEstadoMatricula.ejecutar();
        LOGGER.info(FINALIZACION_ACTUALIZACION);
    }
}
