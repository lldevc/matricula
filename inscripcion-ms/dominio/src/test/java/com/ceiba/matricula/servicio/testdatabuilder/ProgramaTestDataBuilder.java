package com.ceiba.matricula.servicio.testdatabuilder;

import com.ceiba.matricula.modelo.entidad.Programa;

public class ProgramaTestDataBuilder {

    private Long id;
    private String nombre;
    private Double precio;
    private Double recargo;
    private Integer diasParaRecargo;

    public ProgramaTestDataBuilder() {
        this.id = 1111L;
        this.nombre = "Programa Test";
        this.precio = 100000D;
        this.recargo = 0.2;
        this.diasParaRecargo = 5;

    }

    public ProgramaTestDataBuilder conId (Long id){
        this.id = id;
        return this;
    }

    public ProgramaTestDataBuilder conNombre (String nombre){
        this.nombre = nombre;
        return this;
    }

    public ProgramaTestDataBuilder conPrecio (Double precio){
        this.precio = precio;
        return this;
    }

    public ProgramaTestDataBuilder conRecargo (Double recargo){
        this.recargo = recargo;
        return this;
    }

    public ProgramaTestDataBuilder conDiasParaRecargo (Integer diasParaRecargo){
        this.diasParaRecargo = diasParaRecargo;
        return this;
    }

    public ProgramaTestDataBuilder ingles() {
        this.nombre = "Programa Ingles";
        this.precio = 700000D;
        return this;
    }

    public ProgramaTestDataBuilder frences() {
        this.nombre = "Programa Frances";
        this.precio = 850000D;
        return this;
    }

    public ProgramaTestDataBuilder aleman() {
        this.nombre = "Programa Aleman";
        this.precio = 980000D;
        return this;
    }

    public Programa build() {
        return new Programa(id,nombre, precio,recargo, diasParaRecargo);
    }
}
