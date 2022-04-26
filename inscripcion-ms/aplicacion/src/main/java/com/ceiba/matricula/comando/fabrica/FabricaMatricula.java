package com.ceiba.matricula.comando.fabrica;

import com.ceiba.matricula.comando.ComandoMatricula;
import com.ceiba.matricula.modelo.entidad.Matricula;
import org.springframework.stereotype.Component;

@Component
public class FabricaMatricula {

    public Matricula crearPorPrimeraVez(ComandoMatricula comandoMatricula) {
        return new Matricula(
                comandoMatricula.getPrograma(),
                comandoMatricula.getUsuarioMatricula()
        );
    }

    public Matricula crear(ComandoMatricula comandoMatricula) {
        return new Matricula(
                comandoMatricula.getId(),
                comandoMatricula.getValor(),
                comandoMatricula.isRecargo(),
                comandoMatricula.getEstadoDePago(),
                comandoMatricula.getFechaCreacion(),
                comandoMatricula.getFechaLimitePagoSinRecargo(),
                comandoMatricula.getFechaMaximaPago(),
                comandoMatricula.getPrograma(),
                comandoMatricula.getUsuarioMatricula()
        );
    }

}
