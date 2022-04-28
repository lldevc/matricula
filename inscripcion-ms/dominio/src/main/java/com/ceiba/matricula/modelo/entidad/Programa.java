package com.ceiba.matricula.modelo.entidad;


import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Programa {

    private static final String SE_DEBE_INGRESAR_ID = "Debe ingresar un id para el programa";
    private static final String SE_DEBE_INGRESAR_NOMBRE = "Debe ingresar un nombre para el programa";
    private static final String SE_DEBE_INGRESAR_PRECIO = "Debe ingresar un precio para el programa";
    private static final String SE_DEBE_INGRESAR_RECARGO = "Debe ingresar un recargo para el programa";
    private static final String SE_DEBE_INGRESAR_DIAS_DE_RECARGO = "Debe ingresar los dias de plazo para el recargo del programa";
    private static final String EL_VALOR_PROGRAMA_DEBE_SER_POSITIVO = "El valor del programa debe ser positivo";
    private static final String EL_VALOR_RECARGO_DEBE_SER_POSITIVO = "El valor del recargo debe ser positivo";

    private Long id;
    private String nombre;
    private Double precio;
    private Double recargo;
    private Integer diasParaRecargo;

    public Programa() {
    }

    public Programa(Long id, String nombre, Double precio, Double recargo, Integer diasParaRecargo) {
        validarObligatorio(id, SE_DEBE_INGRESAR_ID);
        validarObligatorio(nombre, SE_DEBE_INGRESAR_NOMBRE);
        validarObligatorio(precio, SE_DEBE_INGRESAR_PRECIO);
        validarObligatorio(recargo, SE_DEBE_INGRESAR_RECARGO);
        validarObligatorio(diasParaRecargo, SE_DEBE_INGRESAR_DIAS_DE_RECARGO);
        validarPositivo(precio, EL_VALOR_PROGRAMA_DEBE_SER_POSITIVO);
        validarPositivo(recargo, EL_VALOR_RECARGO_DEBE_SER_POSITIVO);

        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.recargo = recargo;
        this.diasParaRecargo = diasParaRecargo;
    }

    public Double calcularRecargo() {
        return this.precio * this.recargo;
    }

}
