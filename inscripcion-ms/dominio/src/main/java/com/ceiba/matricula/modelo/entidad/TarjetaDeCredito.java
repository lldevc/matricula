package com.ceiba.matricula.modelo.entidad;


import lombok.Getter;

import java.io.Serializable;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class TarjetaDeCredito implements Serializable {

    private static final String SE_DEBE_INGRESAR_NUMERO_DE_TARGETA = "Debe ingresar un numero de targeta";
    private static final String SE_DEBE_INGRESAR_ANIO_VENCIMINETO_TARGETA = "Debe ingresar un año de vencimiento";
    private static final String SE_DEBE_INGRESAR_MES_VENCIMINETO_TARGETA = "Debe ingresar un mes de vencimiento";
    private static final String SE_DEBE_INGRESAR_CODIGO_DE_SEGURIDAD_TARGETA = "Debe ingresar un codigo de seguridad";
    private static final String NUMERO_TARGETA_INVALIDO = "El numero de la tageta ingresado es invalido";
    private static final String ANIO_TARGETA_INVALIDO = "El año ingresado es invalido";
    private static final String MES_TARGETA_INVALIDO = "El mes ingresaso es invalido";
    private static final String CODIGO_SEGURIDAD_TARGETA_INVALIDO = "El codigo de seguridad ingresado es invalido";

    private String numeroTarjeta;
    private String anioVecimiento;
    private String mesVecimiento;
    private String codigoSeguridad;

    public TarjetaDeCredito() {
    }

    public TarjetaDeCredito(String numeroTarjeta, String anioVecimiento, String mesVecimiento, String codigoSeguridad) {
        validarObligatorio(numeroTarjeta, SE_DEBE_INGRESAR_NUMERO_DE_TARGETA);
        validarObligatorio(anioVecimiento, SE_DEBE_INGRESAR_ANIO_VENCIMINETO_TARGETA);
        validarObligatorio(mesVecimiento, SE_DEBE_INGRESAR_MES_VENCIMINETO_TARGETA);
        validarObligatorio(codigoSeguridad, SE_DEBE_INGRESAR_CODIGO_DE_SEGURIDAD_TARGETA);
        validarRegex(numeroTarjeta, "^(?:\\D*\\d){16}\\D*$", NUMERO_TARGETA_INVALIDO);
        validarRegex(anioVecimiento, "^(?:\\D*\\d){4}\\D*$", ANIO_TARGETA_INVALIDO);
        validarRegex(mesVecimiento, "^(?:\\D*\\d){2}\\D*$", MES_TARGETA_INVALIDO);
        validarRegex(codigoSeguridad, "^(?:\\D*\\d){3}\\D*$", CODIGO_SEGURIDAD_TARGETA_INVALIDO);

        this.numeroTarjeta = numeroTarjeta;
        this.anioVecimiento = anioVecimiento;
        this.mesVecimiento = mesVecimiento;
        this.codigoSeguridad = codigoSeguridad;
    }

}
