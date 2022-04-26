package com.ceiba.matricula.servicio.testdatabuilder;

import com.ceiba.matricula.modelo.entidad.TarjetaDeCredito;

public class TarjetaDeCredioTestDataBuilder {

    private String numeroTarjeta;
    private String anioVecimiento;
    private String mesVecimiento;
    private String codigoSeguridad;

    public TarjetaDeCredioTestDataBuilder() {
        this.numeroTarjeta = "1111111111111111";
        this.anioVecimiento = "3022";
        this.mesVecimiento = "01";
        this.codigoSeguridad = "123";
    }

    public TarjetaDeCredioTestDataBuilder conNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
        return this;
    }

    public TarjetaDeCredioTestDataBuilder conAnioVecimiento(String anioVecimiento) {
        this.anioVecimiento = anioVecimiento;
        return this;
    }

    public TarjetaDeCredioTestDataBuilder conMesVecimiento(String mesVecimiento) {
        this.mesVecimiento = mesVecimiento;
        return this;
    }

    public TarjetaDeCredioTestDataBuilder conCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
        return this;
    }

    public TarjetaDeCredito build() {
        return new TarjetaDeCredito(numeroTarjeta,anioVecimiento, mesVecimiento,codigoSeguridad);
    }
}
