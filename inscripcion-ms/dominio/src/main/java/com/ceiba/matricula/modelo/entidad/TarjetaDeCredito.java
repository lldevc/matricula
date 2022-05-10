package com.ceiba.matricula.modelo.entidad;


import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class TarjetaDeCredito {

    private static final String SE_DEBE_INGRESAR_NUMERO_DE_TARJETA = "Debe ingresar un numero de tarjeta";
    private static final String SE_DEBE_INGRESAR_ANIO_VENCIMINETO_TARJETA = "Debe ingresar un año de vencimiento";
    private static final String SE_DEBE_INGRESAR_MES_VENCIMINETO_TARJETA = "Debe ingresar un mes de vencimiento";
    private static final String SE_DEBE_INGRESAR_CODIGO_DE_SEGURIDAD_TARJETA = "Debe ingresar un codigo de seguridad";
    private static final String NUMERO_TARJETA_INVALIDO = "El numero de la tarjeta ingresado es invalido";
    private static final String ANIO_TARJETA_INVALIDO = "El año ingresado es invalido";
    private static final String MES_TARJETA_INVALIDO = "El mes ingresaso es invalido";
    private static final String CODIGO_SEGURIDAD_TARJETA_INVALIDO = "El codigo de seguridad ingresado es invalido";

    private String numeroTarjeta;
    private String anioVecimiento;
    private String mesVecimiento;
    private String codigoSeguridad;

    public TarjetaDeCredito() {
    }

    public TarjetaDeCredito(String numeroTarjeta, String anioVecimiento, String mesVecimiento, String codigoSeguridad) {
        validarObligatorio(numeroTarjeta, SE_DEBE_INGRESAR_NUMERO_DE_TARJETA);
        validarObligatorio(anioVecimiento, SE_DEBE_INGRESAR_ANIO_VENCIMINETO_TARJETA);
        validarObligatorio(mesVecimiento, SE_DEBE_INGRESAR_MES_VENCIMINETO_TARJETA);
        validarObligatorio(codigoSeguridad, SE_DEBE_INGRESAR_CODIGO_DE_SEGURIDAD_TARJETA);
        validarRegex(numeroTarjeta, "^(?:\\D*\\d){16}\\D*$", NUMERO_TARJETA_INVALIDO);
        validarRegex(anioVecimiento, "^(?:\\D*\\d){4}\\D*$", ANIO_TARJETA_INVALIDO);
        validarRegex(mesVecimiento, "^(?:\\D*\\d){2}\\D*$", MES_TARJETA_INVALIDO);
        validarRegex(codigoSeguridad, "^(?:\\D*\\d){3}\\D*$", CODIGO_SEGURIDAD_TARJETA_INVALIDO);
        validarRegex(mesVecimiento, "0[1-9]|1[0-2]", MES_TARJETA_INVALIDO);

        this.numeroTarjeta = numeroTarjeta;
        this.anioVecimiento = anioVecimiento;
        this.mesVecimiento = mesVecimiento;
        this.codigoSeguridad = codigoSeguridad;
    }

}
