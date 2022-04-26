package com.ceiba.matricula.modelo.dto;

import com.ceiba.matricula.modelo.entidad.EstadoDePago;
import com.ceiba.matricula.modelo.entidad.Programa;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoMatricula {
    private Long id;
    private Double valor;
    private boolean recargo;
    private EstadoDePago estadoDePago;
    private final LocalDateTime fechaCreacion;
    private final LocalDateTime fechaLimitePagoSinRecargo;
    private final LocalDateTime fechaMaximaPago;
    private final Programa programa;
    private final UsuarioMatricula usuarioMatricula;

}
