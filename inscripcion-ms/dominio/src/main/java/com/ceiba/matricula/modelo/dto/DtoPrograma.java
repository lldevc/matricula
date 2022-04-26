package com.ceiba.matricula.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoPrograma {
    private Long id;
    private String nombre;
    private Double precio;
    private Double recargo;
    private Integer diasParaRecargo;

}
