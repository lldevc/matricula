package com.ceiba.matricula.consulta.manejador;

import com.ceiba.matricula.modelo.dto.DtoMatricula;
import com.ceiba.matricula.puerto.dao.DaoMatricula;
import com.ceiba.matricula.servicio.ServicioActualizarEstadoMatricula;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorActualizarEstadoMatricula {

    private final ServicioActualizarEstadoMatricula servicioActualizarEstadoMatricula;
    private final DaoMatricula daoMatricula;

    public ManejadorActualizarEstadoMatricula(ServicioActualizarEstadoMatricula servicioActualizarEstadoMatricula, DaoMatricula daoMatricula) {
        this.servicioActualizarEstadoMatricula = servicioActualizarEstadoMatricula;
        this.daoMatricula = daoMatricula;
    }

    public void ejecutar() {
        List<DtoMatricula> matriculas = this.daoMatricula.listarMatriculas();
        this.servicioActualizarEstadoMatricula.ejecutar(matriculas);
    }
}
