package com.ceiba.matricula.comando;

import com.ceiba.matricula.modelo.entidad.EstadoDePago;
import com.ceiba.matricula.modelo.entidad.Programa;
import com.ceiba.matricula.modelo.entidad.TarjetaDeCredito;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoPagarMatricula {

    private Long id;
    private Programa programa;
    private UsuarioMatricula usuarioMatricula;
    private TarjetaDeCredito medioDePago;
}
