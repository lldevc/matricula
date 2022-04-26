package com.ceiba.matricula.consulta.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.matricula.comando.ComandoUsuarioMatricula;
import com.ceiba.matricula.comando.fabrica.FabricaUsuarioMatricula;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;
import com.ceiba.matricula.servicio.ServicioCrearUsuarioMatricula;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearUsuarioMatricula implements ManejadorComandoRespuesta<ComandoUsuarioMatricula, ComandoRespuesta<Long>> {

    private final FabricaUsuarioMatricula fabricaUsuarioMatricula;
    private final ServicioCrearUsuarioMatricula servicioCrearUsuarioMatricula;

    public ManejadorCrearUsuarioMatricula(FabricaUsuarioMatricula fabricaUsuarioMatricula, ServicioCrearUsuarioMatricula servicioCrearUsuarioMatricula) {
        this.fabricaUsuarioMatricula = fabricaUsuarioMatricula;
        this.servicioCrearUsuarioMatricula = servicioCrearUsuarioMatricula;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoUsuarioMatricula comandoUsuarioMatricula) {
        UsuarioMatricula usuarioMatricula = this.fabricaUsuarioMatricula.crear(comandoUsuarioMatricula);
        return new ComandoRespuesta<>(this.servicioCrearUsuarioMatricula.ejecutar(usuarioMatricula));
    }
}
