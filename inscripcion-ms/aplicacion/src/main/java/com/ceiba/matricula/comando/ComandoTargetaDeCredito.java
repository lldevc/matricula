package com.ceiba.matricula.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoTargetaDeCredito {

    private String numeroTargeta;
    private String anioVecimiento;
    private String mesVecimiento;
    private String codigoSeguridad;
}
