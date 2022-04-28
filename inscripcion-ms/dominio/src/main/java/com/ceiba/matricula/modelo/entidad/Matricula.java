package com.ceiba.matricula.modelo.entidad;


import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static java.time.DayOfWeek.*;
import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Matricula {

    private static final String SE_DEBE_INGRESAR_ID = "La matricula debe tener un id";
    private static final String SE_DEBE_INGRESAR_VALOR = "La matricula debe tener un valor";
    private static final String SE_DEBE_INGRESAR_RECARGO = "La matricula debe tener un recargo";
    private static final String SE_DEBE_INGRESAR_DIAS_PARA_PAGAR = "La matricula debe tener un numero de dias para pagar";
    private static final String SE_DEBE_INGRESAR_PROGRAMA = "La matricula debe tener un programa";
    private static final String SE_DEBE_INGRESAR_USUARIO = "La matricula debe tener un estudiante";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_CREACION = "Se debe ingresar la fecha de creación";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_PAGO_SIN_RECARGO = "Se debe ingresar la fecha de pago con recargo";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_PAGO_MAXIMA = "Se debe ingresar la fecha de pago maxima";
    private static final String SE_DEBE_INGRESAR_ESTADO = "Se debe ingresar el estado de la matricula";
    private static final String EL_VALOR_POSITIVO = "La matricula debe tener un valor positivo";

    private Long id;

    private Double valor;

    @Setter
    private boolean recargo;

    @Setter
    private EstadoDePago estadoDePago;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaLimitePagoSinRecargo;

    private LocalDateTime fechaMaximaPago;

    private Programa programa;

    private UsuarioMatricula usuarioMatricula;

    public Matricula(Programa programa, UsuarioMatricula usuarioMatricula) {
        validarObligatorio(programa, SE_DEBE_INGRESAR_PROGRAMA);
        validarObligatorio(usuarioMatricula, SE_DEBE_INGRESAR_USUARIO);

        this.programa = programa;
        this.valor = programa.getPrecio();
        this.usuarioMatricula = usuarioMatricula;
        this.recargo = false;
        this.estadoDePago = EstadoDePago.PENDIENTE;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaLimitePagoSinRecargo = calcularFechaLimitePago(fechaCreacion, programa.getDiasParaRecargo());
        this.fechaMaximaPago = calcularFechaLimitePago(fechaCreacion, programa.getDiasParaRecargo() + 5);
    }

    public Matricula(Long id, Double valor, boolean recargo, EstadoDePago estadoDePago, LocalDateTime fechaCreacion, LocalDateTime fechaLimitePagoSinRecargo, LocalDateTime fechaMaximaPago, Programa programa, UsuarioMatricula usuarioMatricula) {
        validarObligatorio(id, SE_DEBE_INGRESAR_ID);
        validarObligatorio(valor, SE_DEBE_INGRESAR_VALOR);
        validarObligatorio(recargo, SE_DEBE_INGRESAR_RECARGO);
        validarObligatorio(estadoDePago, SE_DEBE_INGRESAR_ESTADO);
        validarObligatorio(programa, SE_DEBE_INGRESAR_PROGRAMA);
        validarObligatorio(usuarioMatricula, SE_DEBE_INGRESAR_USUARIO);
        validarObligatorio(fechaCreacion, SE_DEBE_INGRESAR_LA_FECHA_CREACION);
        validarObligatorio(fechaLimitePagoSinRecargo, SE_DEBE_INGRESAR_LA_FECHA_PAGO_SIN_RECARGO);
        validarObligatorio(fechaMaximaPago, SE_DEBE_INGRESAR_LA_FECHA_PAGO_MAXIMA);
        validarPositivo(valor, EL_VALOR_POSITIVO);

        this.id = id;
        this.valor = valor;
        this.recargo = recargo;
        this.estadoDePago = estadoDePago;
        this.fechaCreacion = fechaCreacion;
        this.fechaLimitePagoSinRecargo = fechaLimitePagoSinRecargo;
        this.fechaMaximaPago = fechaMaximaPago;
        this.programa = programa;
        this.usuarioMatricula = usuarioMatricula;
    }

    public void setValor(Double valor) {
        validarPositivo(valor, EL_VALOR_POSITIVO);
        this.valor = valor;
    }

    private LocalDateTime calcularFechaLimitePago(LocalDateTime fechaBase, Integer diasParaPagarPrograma) {
        int anio = fechaBase.getYear();
        int mes = fechaBase.getMonthValue();
        int diaMes = fechaBase.getDayOfMonth();
        LocalDateTime fechaCalculada = LocalDateTime.of(anio, mes, diaMes, 23, 59, 59);
        return fechaCalculada.plusDays(diasASumar(fechaCalculada, diasParaPagarPrograma));
    }

    private Integer diasASumar (LocalDateTime fecha, int diasParaPagarPrograma) {
        List<String> diasHabiles = Arrays.asList("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY");
        int diasDemas = 1;
        int diasASumar = 1;         //Empieza en 1 porque los dias empiezan a contar al siguiente dia habil desde la fecha de inscripcion
        DayOfWeek dia;

        if (esDiaNoHabil(fecha) || FRIDAY.toString().equals(fecha.getDayOfWeek().toString()) ){
            dia = fecha.getDayOfWeek();
            if (FRIDAY.toString().equals(dia.toString())){
                diasASumar = 3;
            }
            else if(SATURDAY.toString().equals(dia.toString())){
                diasASumar = 2;
            }
        }

        while (diasDemas < diasParaPagarPrograma){
            diasASumar++;
            dia = fecha.plusDays(diasASumar).getDayOfWeek();
            if(diasHabiles.contains(dia.toString())){
                diasDemas++;
            }
        }

        return diasASumar;
    }

    private boolean esDiaNoHabil(LocalDateTime fecha){
        DayOfWeek dia = fecha.getDayOfWeek();
        return "SATURDAY".equals(dia.toString()) || "SUNDAY".equals(dia.toString());
    }
}
