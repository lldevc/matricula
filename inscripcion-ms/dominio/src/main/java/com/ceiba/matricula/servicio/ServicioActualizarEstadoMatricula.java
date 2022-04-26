package com.ceiba.matricula.servicio;

import com.ceiba.matricula.modelo.dto.DtoMatricula;
import com.ceiba.matricula.modelo.entidad.EstadoDePago;
import com.ceiba.matricula.modelo.entidad.Matricula;
import com.ceiba.matricula.puerto.repositorio.RepositorioMatricula;
import com.ceiba.matricula.puerto.repositorio.RepositorioUsuarioMatricula;
import com.ceiba.matricula.util.MapperMatricula;

import java.time.LocalDateTime;
import java.util.List;

public class ServicioActualizarEstadoMatricula {

    private final RepositorioMatricula repositorioMatricula;
    private final RepositorioUsuarioMatricula repositorioUsuarioMatricula;

    public ServicioActualizarEstadoMatricula(RepositorioMatricula repositorioMatricula, RepositorioUsuarioMatricula repositorioUsuarioMatricula) {
        this.repositorioMatricula = repositorioMatricula;
        this.repositorioUsuarioMatricula = repositorioUsuarioMatricula;
    }

    public void ejecutar(List<DtoMatricula> dtoMatriculas) {
        Matricula matricula;
        for (DtoMatricula dtoMatricula: dtoMatriculas) {
            matricula = MapperMatricula.mapperDtoMatriculaToMatricula(dtoMatricula);
            validarEstadoDeMatricula(matricula);
        }
    }

    private void validarEstadoDeMatricula(Matricula matricula) {

        boolean pagada = matricula.getEstadoDePago().equals(EstadoDePago.PAGADA);

        if(!pagada) {
            boolean fechaMaximaPagoVencida = matricula.getFechaMaximaPago().isBefore(LocalDateTime.now());
            boolean fechaPagoVencida = matricula.getFechaLimitePagoSinRecargo().isBefore(LocalDateTime.now());
            boolean vencida = matricula.getEstadoDePago().equals(EstadoDePago.VENCIDA);
            if (fechaMaximaPagoVencida && !vencida) {
                matricula.setEstadoDePago(EstadoDePago.VENCIDA);
                LocalDateTime fechaSancionUsuario = matricula.getFechaMaximaPago().plusMonths(6);
                matricula.getUsuarioMatricula().setFechaSancion(fechaSancionUsuario);
                this.repositorioUsuarioMatricula.actualizar(matricula.getUsuarioMatricula());
                this.repositorioMatricula.actualizar(matricula);

            } else if (fechaPagoVencida && !vencida) {
                if(!matricula.isRecargo()) {
                    matricula.setRecargo(true);
                    Double valorRecargo = matricula.getPrograma().calcularRecargo();
                    Double nuevoValor = matricula.getValor() + valorRecargo;
                    matricula.setValor(nuevoValor);
                    this.repositorioMatricula.actualizar(matricula);
                }
            }
        }
    }
}
