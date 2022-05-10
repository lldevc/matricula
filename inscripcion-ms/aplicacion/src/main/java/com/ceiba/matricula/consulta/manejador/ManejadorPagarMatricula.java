package com.ceiba.matricula.consulta.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.matricula.comando.ComandoMatricula;
import com.ceiba.matricula.comando.fabrica.FabricaMatricula;
import com.ceiba.matricula.comando.fabrica.FabricaTarjetaDeCredito;
import com.ceiba.matricula.modelo.entidad.Matricula;
import com.ceiba.matricula.modelo.entidad.TarjetaDeCredito;
import com.ceiba.matricula.servicio.ServicioPagarMatricula;
import org.springframework.stereotype.Component;

@Component
public class ManejadorPagarMatricula implements ManejadorComando<ComandoMatricula> {

    private final FabricaMatricula fabricaMatricula;
    private final FabricaTarjetaDeCredito fabricaTarjetaDeCredito;
    private final ServicioPagarMatricula servicioPagarMatricula;

    public ManejadorPagarMatricula(FabricaMatricula fabricaMatricula, FabricaTarjetaDeCredito fabricaTarjetaDeCredito, ServicioPagarMatricula servicioPagarMatricula) {
        this.fabricaMatricula = fabricaMatricula;
        this.fabricaTarjetaDeCredito = fabricaTarjetaDeCredito;
        this.servicioPagarMatricula = servicioPagarMatricula;
    }

    public void ejecutar(ComandoMatricula comandoMatricula) {
        Matricula matricula = this.fabricaMatricula.crear(comandoMatricula);
        TarjetaDeCredito tarjetaDeCredito = this.fabricaTarjetaDeCredito.crear(comandoMatricula);
        this.servicioPagarMatricula.ejecutar(matricula, tarjetaDeCredito);
    }
}
