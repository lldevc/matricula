package com.ceiba.matricula.consulta.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.matricula.comando.ComandoUsuarioMatricula;
import com.ceiba.matricula.comando.fabrica.FabricaUsuarioMatricula;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;
import com.ceiba.matricula.servicio.ServicioActualizarUsuarioMatricula;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarUsuarioMatricula implements ManejadorComando<ComandoUsuarioMatricula> {

    private final FabricaUsuarioMatricula fabricaUsuarioMatricula;
    private final ServicioActualizarUsuarioMatricula servicioActualizarUsuarioMatricula;

    public ManejadorActualizarUsuarioMatricula(FabricaUsuarioMatricula fabricaUsuarioMatricula, ServicioActualizarUsuarioMatricula servicioActualizarUsuarioMatricula) {
        this.fabricaUsuarioMatricula = fabricaUsuarioMatricula;
        this.servicioActualizarUsuarioMatricula = servicioActualizarUsuarioMatricula;
    }

    public void ejecutar(ComandoUsuarioMatricula comandoUsuarioMatricula) {
        UsuarioMatricula usuarioMatricula = this.fabricaUsuarioMatricula.crear(comandoUsuarioMatricula);
        this.servicioActualizarUsuarioMatricula.ejecutar(usuarioMatricula);
    }
}
