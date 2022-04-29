package com.ceiba.matricula.util;

import com.ceiba.matricula.modelo.dto.DtoMatricula;
import com.ceiba.matricula.modelo.entidad.EstadoDePago;
import com.ceiba.matricula.modelo.entidad.Matricula;

public final class MapperMatricula {

    public static DtoMatricula mapperMatriculaToDtoMatricula(Matricula matricula) {
        return new DtoMatricula(
                matricula.getId(),
                matricula.getValor(),
                matricula.isRecargo(),
                matricula.getEstadoDePago(),
                matricula.getFechaCreacion(),
                matricula.getFechaLimitePagoSinRecargo(),
                matricula.getFechaMaximaPago(),
                matricula.getPrograma(),
                matricula.getUsuarioMatricula()
        );
    }

    public static Matricula mapperDtoMatriculaToMatricula(DtoMatricula dtoMatricula) {
        return new Matricula(
                dtoMatricula.getId(),
                dtoMatricula.getValor(),
                dtoMatricula.isRecargo(),
                dtoMatricula.getEstadoDePago(),
                dtoMatricula.getFechaCreacion(),
                dtoMatricula.getFechaLimitePagoSinRecargo(),
                dtoMatricula.getFechaMaximaPago(),
                dtoMatricula.getPrograma(),
                dtoMatricula.getUsuarioMatricula()
        );
    }

}
