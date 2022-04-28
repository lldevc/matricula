package com.ceiba.matricula.comando.fabrica;

import com.ceiba.matricula.comando.ComandoCrearMatricula;
import com.ceiba.matricula.comando.ComandoMatricula;
import com.ceiba.matricula.modelo.entidad.Matricula;
import org.springframework.stereotype.Component;

@Component
public class FabricaMatricula {

    public Matricula crear(ComandoCrearMatricula comandoCrearMatricula) {
        return new Matricula(
                comandoCrearMatricula.getPrograma(),
                comandoCrearMatricula.getUsuarioMatricula()
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
