package com.ceiba.matricula.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionUsuarioSancionado;
import com.ceiba.matricula.modelo.dto.DtoUsuarioMatricula;
import com.ceiba.matricula.modelo.entidad.Matricula;
import com.ceiba.matricula.puerto.dao.DaoUsuarioMatricula;
import com.ceiba.matricula.puerto.repositorio.RepositorioMatricula;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ServicioCrearMatricula {

    private static final String EL_USUARIO_YA_TIENE_MATRICULA_PARA_ESTE_PROGRAMA= "El usuario ya tiene una matricula registrada para el programa de %s en el sistema";
    private static final String EL_ASPIRANTE_ESTA_SANCIONADO = "el aspirante esta sancionado por no pago de matricula hasta la fecha: %s";

    private final RepositorioMatricula repositorioMatricula;
    private final DaoUsuarioMatricula daoUsuarioMatricula;
    private final ServicioCrearUsuarioMatricula servicioCrearUsuarioMatricula;


    public ServicioCrearMatricula(RepositorioMatricula repositorioMatricula, DaoUsuarioMatricula daoUsuarioMatricula, ServicioCrearUsuarioMatricula servicioCrearUsuarioMatricula) {
        this.repositorioMatricula = repositorioMatricula;
        this.daoUsuarioMatricula = daoUsuarioMatricula;
        this.servicioCrearUsuarioMatricula = servicioCrearUsuarioMatricula;
    }

    public Long ejecutar(Matricula matricula) {
        boolean usuarioRegistrado = servicioCrearUsuarioMatricula.validarEstaRegistrado(matricula.getUsuarioMatricula());
        Long usuarioId;
        if(usuarioRegistrado){
            Long numeroIdentificacionUsuario = matricula.getUsuarioMatricula().getNumeroIdentificacion();
            String nombreProgramaMatricula = matricula.getPrograma().getNombre();
            usuarioId = servicioCrearUsuarioMatricula.validarSiUsuarioTieneMatricula(numeroIdentificacionUsuario, nombreProgramaMatricula);
        } else {
            usuarioId = servicioCrearUsuarioMatricula.ejecutar(matricula.getUsuarioMatricula());
        }
        matricula.getUsuarioMatricula().setId(usuarioId);
        validarSiEstaSancionado(matricula.getUsuarioMatricula().getId());
        validarExistenciaPrevia(matricula);
        return this.repositorioMatricula.crear(matricula);
    }

    private void validarExistenciaPrevia(Matricula matricula) {
        boolean existe = this.repositorioMatricula.existePorId(matricula.getId());
        if(existe) {
            throw new ExcepcionDuplicidad(String.format(EL_USUARIO_YA_TIENE_MATRICULA_PARA_ESTE_PROGRAMA, matricula.getPrograma().getNombre()));
        }
    }

    private void validarSiEstaSancionado(Long id) {
        DtoUsuarioMatricula usuarioMatricula = this.daoUsuarioMatricula.listarPorId(id);
        LocalDateTime fechaSancion = usuarioMatricula.getFechaSancion();

        if((fechaSancion != null)) {
            boolean fechaSancionPaso = fechaSancion.isBefore(LocalDateTime.now());
            if(!fechaSancionPaso){
                throw new ExcepcionUsuarioSancionado(String.format(EL_ASPIRANTE_ESTA_SANCIONADO, fechaSancion.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))));
            }
        }
    }
}
