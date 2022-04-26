package com.ceiba.matricula.consulta.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.matricula.comando.ComandoMatricula;
import com.ceiba.matricula.comando.fabrica.FabricaMatricula;
import com.ceiba.matricula.consulta.ManejadorListarMatricula;
import com.ceiba.matricula.modelo.entidad.Matricula;
import com.ceiba.matricula.servicio.ServicioCrearMatricula;
import com.ceiba.matricula.servicio.ServicioCrearUsuarioMatricula;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearMatricula implements ManejadorComandoRespuesta<ComandoMatricula, ComandoRespuesta<Long>> {

    private final FabricaMatricula fabricaMatricula;
    private final ServicioCrearMatricula servicioCrearMatricula;
    private final ServicioCrearUsuarioMatricula servicioCrearUsuarioMatricula;



    public ManejadorCrearMatricula(FabricaMatricula fabricaMatricula, ServicioCrearMatricula servicioCrearMatricula, ServicioCrearUsuarioMatricula servicioCrearUsuarioMatricula) {
        this.fabricaMatricula = fabricaMatricula;
        this.servicioCrearMatricula = servicioCrearMatricula;
        this.servicioCrearUsuarioMatricula = servicioCrearUsuarioMatricula;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoMatricula comandoMatricula) {
        boolean usuarioRegistrado = servicioCrearUsuarioMatricula.validarEstaRegistrado(comandoMatricula.getUsuarioMatricula());
        Long usuarioId;
        if(usuarioRegistrado){
            Long numeroIdentificacionUsuario = comandoMatricula.getUsuarioMatricula().getNumeroIdentificacion();
            String nombreProgramaMatricula = comandoMatricula.getPrograma().getNombre();
            usuarioId = servicioCrearUsuarioMatricula.validarSiUsuarioTieneMatricula(numeroIdentificacionUsuario, nombreProgramaMatricula);
        } else {
            usuarioId = servicioCrearUsuarioMatricula.ejecutar(comandoMatricula.getUsuarioMatricula());
        }
        Long programaId = comandoMatricula.getPrograma().getId();
        Matricula matricula = this.fabricaMatricula.crearPorPrimeraVez(comandoMatricula);
        return new ComandoRespuesta<>(this.servicioCrearMatricula.ejecutar(matricula, programaId, usuarioId));
    }
}
