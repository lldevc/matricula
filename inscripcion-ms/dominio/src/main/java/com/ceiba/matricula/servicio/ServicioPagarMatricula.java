package com.ceiba.matricula.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.matricula.modelo.entidad.EstadoDePago;
import com.ceiba.matricula.modelo.entidad.Matricula;
import com.ceiba.matricula.modelo.entidad.TarjetaDeCredito;
import com.ceiba.matricula.puerto.repositorio.RepositorioMatricula;

import java.time.LocalDate;

public class ServicioPagarMatricula {

    private static final String LA_MATRICULA_NO_EXISTE_EN_EL_SISTEMA = "La matricula no existe en el sistema";
    private static final String LA_TARJETA_INGRESADA_ESTA_VENCIDA = "La tarjeta ingreasada para el pago de la matricula está vencida";

    private final RepositorioMatricula repositorioMatricula;

    public ServicioPagarMatricula(RepositorioMatricula repositorioMatricula) {
        this.repositorioMatricula = repositorioMatricula;
    }

    public void ejecutar(Matricula matricula, TarjetaDeCredito tarjetaDeCredito) {
        validarExistenciaPrevia(matricula);
        validarFechaDeVencimientoDeTargetaCredito(tarjetaDeCredito);
        matricula.setEstadoDePago(EstadoDePago.PAGADA);
        this.repositorioMatricula.actualizar(matricula);
    }

    private void validarExistenciaPrevia(Matricula matricula) {
        boolean existe = this.repositorioMatricula.existePorId(matricula.getId());
        if(!existe) {
            throw new ExcepcionDuplicidad(LA_MATRICULA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    private void validarFechaDeVencimientoDeTargetaCredito(TarjetaDeCredito tarjetaDeCredito){
        String anio = tarjetaDeCredito.getAnioVecimiento();
        String mes = tarjetaDeCredito.getMesVecimiento();
        String fechaFormat = String.format("%s-%s-01", anio, mes);

        LocalDate fechaVencimientoTarjeta = LocalDate.parse(fechaFormat);
        boolean fechaVencida = fechaVencimientoTarjeta.isBefore(LocalDate.now());
        if(fechaVencida){
            throw new ExcepcionValorInvalido(LA_TARJETA_INGRESADA_ESTA_VENCIDA);
        }
    }
}
