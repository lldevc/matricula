package com.ceiba.matricula.servicio.testdatabuilder;

import com.ceiba.matricula.modelo.entidad.EstadoDePago;
import com.ceiba.matricula.modelo.entidad.Matricula;
import com.ceiba.matricula.modelo.entidad.Programa;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;

public class MatriculaTestDataBuilder {

    private Long id;
    private Double valor;
    private boolean recargo;
    private EstadoDePago estadoDePago;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaLimitePagoSinRecargo;
    private LocalDateTime fechaMaximaPago;
    private Programa programa;
    private UsuarioMatricula usuarioMatricula;

    public MatriculaTestDataBuilder(Programa programa, UsuarioMatricula usuarioMatricula) {
        this.id = 1234L;
        this.valor = programa.getPrecio();
        this.recargo = false;
        this.estadoDePago = EstadoDePago.PENDIENTE;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaLimitePagoSinRecargo = calcularFechaLimitePago(fechaCreacion, programa.getDiasParaRecargo());
        this.fechaMaximaPago = calcularFechaLimitePago(fechaCreacion, programa.getDiasParaRecargo() + 5);
        this.programa = programa;
        this.usuarioMatricula = usuarioMatricula;
    }

    public MatriculaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public MatriculaTestDataBuilder conPrograma(Programa programa) {
        this.programa = programa;
        return this;
    }

    public MatriculaTestDataBuilder conValor(Double valor) {
        this.valor = valor;
        return this;
    }

    public MatriculaTestDataBuilder conUsuarioMatricula(UsuarioMatricula usuarioMatricula) {
        this.usuarioMatricula = usuarioMatricula;
        return this;
    }

    public MatriculaTestDataBuilder conRecargo(boolean recargo) {
        this.recargo = recargo;
        return this;
    }

    public MatriculaTestDataBuilder conEstadoDePago(EstadoDePago estadoDePago) {
        this.estadoDePago = estadoDePago;
        return this;
    }

    public MatriculaTestDataBuilder conFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public MatriculaTestDataBuilder conFechaLimitePagoConRecargo(LocalDateTime fechaLimitePagoSinRecargo) {
        this.fechaLimitePagoSinRecargo = fechaLimitePagoSinRecargo;
        return this;
    }

    public MatriculaTestDataBuilder conFechaMaximaPago(LocalDateTime fechaMaximaPago) {
        this.fechaMaximaPago = fechaMaximaPago;
        return this;
    }

    public Matricula build() {
        return new Matricula(id, valor, recargo,estadoDePago, fechaCreacion, fechaLimitePagoSinRecargo, fechaMaximaPago, programa, usuarioMatricula);
    }

    public Matricula build2() {
        return new Matricula(programa, usuarioMatricula);
    }

    public static LocalDateTime calcularFechaLimitePago(LocalDateTime fechaBase, Integer diasParaPagarPrograma) {
        int anio = fechaBase.getYear();
        int mes = fechaBase.getMonthValue();
        int diaMes = fechaBase.getDayOfMonth();
        LocalDateTime fechaCalculada = LocalDateTime.of(anio, mes, diaMes, 23, 59, 59);
        return fechaCalculada.plusDays(diasASumar(fechaCalculada, diasParaPagarPrograma));
    }

    public static Integer diasASumar (LocalDateTime fecha, int diasParaPagarPrograma) {
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

    public static boolean esDiaNoHabil(LocalDateTime fecha){
        DayOfWeek dia = fecha.getDayOfWeek();
        return "SATURDAY".equals(dia.toString()) || "SUNDAY".equals(dia.toString());
    }
}
