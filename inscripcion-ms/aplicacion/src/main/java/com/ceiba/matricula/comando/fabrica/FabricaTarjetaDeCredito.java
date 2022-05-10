package com.ceiba.matricula.comando.fabrica;

import com.ceiba.matricula.comando.ComandoMatricula;
import com.ceiba.matricula.modelo.entidad.TarjetaDeCredito;
import org.springframework.stereotype.Component;

@Component
public class FabricaTarjetaDeCredito {

    public TarjetaDeCredito crear(ComandoMatricula comandoMatricula) {
        return new TarjetaDeCredito(
                comandoMatricula.getMedioDePago().getNumeroTarjeta(),
                comandoMatricula.getMedioDePago().getAnioVecimiento(),
                comandoMatricula.getMedioDePago().getMesVecimiento(),
                comandoMatricula.getMedioDePago().getCodigoSeguridad()
        );
    }

}
