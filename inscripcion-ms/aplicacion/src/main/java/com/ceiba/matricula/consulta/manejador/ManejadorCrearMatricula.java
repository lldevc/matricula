package com.ceiba.matricula.consulta.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.matricula.comando.ComandoCrearMatricula;
import com.ceiba.matricula.comando.ComandoMatricula;
import com.ceiba.matricula.comando.fabrica.FabricaMatricula;
import com.ceiba.matricula.consulta.ManejadorListarMatricula;
import com.ceiba.matricula.modelo.entidad.Matricula;
import com.ceiba.matricula.servicio.ServicioCrearMatricula;
import com.ceiba.matricula.servicio.ServicioCrearUsuarioMatricula;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearMatricula implements ManejadorComandoRespuesta<ComandoCrearMatricula, ComandoRespuesta<Long>> {

    private final FabricaMatricula fabricaMatricula;
    private final ServicioCrearMatricula servicioCrearMatricula;
    private final ServicioCrearUsuarioMatricula servicioCrearUsuarioMatricula;



    public ManejadorCrearMatricula(FabricaMatricula fabricaMatricula, ServicioCrearMatricula servicioCrearMatricula, ServicioCrearUsuarioMatricula servicioCrearUsuarioMatricula) {
        this.fabricaMatricula = fabricaMatricula;
        this.servicioCrearMatricula = servicioCrearMatricula;
        this.servicioCrearUsuarioMatricula = servicioCrearUsuarioMatricula;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoCrearMatricula comandoCrearMatricula) {
        Matricula matricula = this.fabricaMatricula.crear(comandoCrearMatricula);
        return new ComandoRespuesta<>(this.servicioCrearMatricula.ejecutar(matricula));
    }
}
