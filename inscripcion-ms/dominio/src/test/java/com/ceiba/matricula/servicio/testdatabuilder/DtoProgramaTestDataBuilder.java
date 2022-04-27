package com.ceiba.matricula.servicio.testdatabuilder;

import com.ceiba.matricula.modelo.dto.DtoPrograma;

public class DtoProgramaTestDataBuilder {

    private Long id;
    private String nombre;
    private Double precio;
    private Double recargo;
    private Integer diasParaRecargo;

    public DtoProgramaTestDataBuilder() {
        this.id = 1111L;
        this.nombre = "Programa Test";
        this.precio = 100000D;
        this.recargo = 0.2;
        this.diasParaRecargo = 5;

    }

    public DtoProgramaTestDataBuilder conId (Long id){
        this.id = id;
        return this;
    }

    public DtoProgramaTestDataBuilder conNombre (String nombre){
        this.nombre = nombre;
        return this;
    }

    public DtoProgramaTestDataBuilder conPrecio (Double precio){
        this.precio = precio;
        return this;
    }

    public DtoProgramaTestDataBuilder conRecargo (Double recargo){
        this.recargo = recargo;
        return this;
    }

    public DtoProgramaTestDataBuilder conDiasParaRecargo (Integer diasParaRecargo){
        this.diasParaRecargo = diasParaRecargo;
        return this;
    }

    public DtoProgramaTestDataBuilder ingles() {
        this.nombre = "Programa Ingles";
        this.precio = 700000D;
        return this;
    }

    public DtoProgramaTestDataBuilder frences() {
        this.nombre = "Programa Frances";
        this.precio = 850000D;
        return this;
    }

    public DtoProgramaTestDataBuilder aleman() {
        this.nombre = "Programa Aleman";
        this.precio = 980000D;
        return this;
    }

    public DtoPrograma build() {
        return new DtoPrograma(id,nombre, precio,recargo, diasParaRecargo);
    }
}
