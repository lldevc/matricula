package com.ceiba.matricula.servicio.testdatabuilder;

import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;

import java.time.LocalDateTime;

public class UsuarioMatriculaTestDataBuilder {

    private Long id;
    private Long numeroIdentificacion;
    private String nombre;
    private String email;
    private String ciudad;
    private String direccion;
    private LocalDateTime fechaSancion;

    public UsuarioMatriculaTestDataBuilder() {
        this.id = 1234L;
        this.numeroIdentificacion = 1234L;
        this.nombre = "1234";
        this.email = "1234@test.com";
        this.ciudad = "1234";
        this.direccion = "1234";
        this.fechaSancion = null;
    }

    public UsuarioMatriculaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public UsuarioMatriculaTestDataBuilder conNumeroIdentificacion(Long numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
        return this;
    }

    public UsuarioMatriculaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public UsuarioMatriculaTestDataBuilder conEmail(String email) {
        this.email = email;
        return this;
    }

    public UsuarioMatricula build() {
        return new UsuarioMatricula(id, numeroIdentificacion, nombre, email, ciudad, direccion);
    }
}
