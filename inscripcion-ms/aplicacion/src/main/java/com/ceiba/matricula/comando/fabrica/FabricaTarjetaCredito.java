package com.ceiba.matricula.comando.fabrica;

import com.ceiba.matricula.comando.ComandoTargetaDeCredito;
import com.ceiba.matricula.modelo.entidad.TarjetaDeCredito;
import org.springframework.stereotype.Component;

@Component
public class FabricaTarjetaCredito {

    public TarjetaDeCredito crear(ComandoTargetaDeCredito comandoTargetaDeCredito) {
        return new TarjetaDeCredito(
                comandoTargetaDeCredito.getNumeroTargeta(),
                comandoTargetaDeCredito.getAnioVecimiento(),
                comandoTargetaDeCredito.getMesVecimiento(),
                comandoTargetaDeCredito.getCodigoSeguridad()
        );
    }

}
