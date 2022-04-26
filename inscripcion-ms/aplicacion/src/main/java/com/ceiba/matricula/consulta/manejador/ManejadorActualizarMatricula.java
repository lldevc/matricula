package com.ceiba.matricula.consulta.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.matricula.comando.ComandoMatricula;
import com.ceiba.matricula.comando.fabrica.FabricaMatricula;
import com.ceiba.matricula.comando.fabrica.FabricaTarjetaCredito;
import com.ceiba.matricula.modelo.dto.DtoMatricula;
import com.ceiba.matricula.modelo.entidad.Matricula;
import com.ceiba.matricula.modelo.entidad.TarjetaDeCredito;
import com.ceiba.matricula.puerto.dao.DaoMatricula;
import com.ceiba.matricula.servicio.ServicioActualizarEstadoMatricula;
import com.ceiba.matricula.servicio.ServicioActualizarMatricula;
import com.ceiba.matricula.servicio.ServicioPagarMatricula;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorActualizarMatricula implements ManejadorComando<ComandoMatricula> {

    private final FabricaMatricula fabricaMatricula;
    private final FabricaTarjetaCredito fabricaTarjetaCredito;
    private final ServicioActualizarMatricula servicioActualizarMatricula;
    private final ServicioActualizarEstadoMatricula servicioActualizarEstadoMatricula;
    private final ServicioPagarMatricula servicioPagarMatricula;
    private final DaoMatricula daoMatricula;

    public ManejadorActualizarMatricula(FabricaMatricula fabricaMatricula, FabricaTarjetaCredito fabricaTarjetaCredito, ServicioActualizarMatricula servicioActualizarMatricula, ServicioActualizarEstadoMatricula servicioActualizarEstadoMatricula, ServicioPagarMatricula servicioPagarMatricula, DaoMatricula daoMatricula) {
        this.fabricaMatricula = fabricaMatricula;
        this.fabricaTarjetaCredito = fabricaTarjetaCredito;
        this.servicioActualizarMatricula = servicioActualizarMatricula;
        this.servicioActualizarEstadoMatricula = servicioActualizarEstadoMatricula;
        this.servicioPagarMatricula = servicioPagarMatricula;
        this.daoMatricula = daoMatricula;
    }

    public void ejecutar(ComandoMatricula comandoMatricula) {
        Matricula matricula = this.fabricaMatricula.crear(comandoMatricula);
        this.servicioActualizarMatricula.ejecutar(matricula);
    }

    public void pagarMatricula(ComandoMatricula comandoMatricula) {
        Matricula matricula = this.fabricaMatricula.crear(comandoMatricula);
        TarjetaDeCredito tarjetaDeCredito = this.fabricaTarjetaCredito.crear(comandoMatricula.getMedioDePago());
        this.servicioPagarMatricula.ejecutar(matricula, tarjetaDeCredito);
    }

    public void actualizarEstadoMatriculas() {
        List<DtoMatricula> matriculas = this.daoMatricula.listarMatriculas();
        this.servicioActualizarEstadoMatricula.ejecutar(matriculas);
    }
}
