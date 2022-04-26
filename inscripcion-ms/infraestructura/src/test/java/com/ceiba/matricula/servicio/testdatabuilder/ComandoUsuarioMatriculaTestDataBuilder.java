package com.ceiba.matricula.servicio.testdatabuilder;

import com.ceiba.matricula.comando.ComandoUsuarioMatricula;

public class ComandoUsuarioMatriculaTestDataBuilder {

    private Long id;
    private Long numeroIdentificacion;
    private String nombre;
    private String email;
    private String ciudad;
    private String direccion;

    public ComandoUsuarioMatriculaTestDataBuilder() {
        this.nombre = "test";
        this.numeroIdentificacion = 1234L;
        this.email = "test@test.com";
        this.ciudad = "test";
        this.direccion = "test";
    }

    public ComandoUsuarioMatriculaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ComandoUsuarioMatriculaTestDataBuilder conNumeroIdentificacion(Long numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
        return this;
    }

    public ComandoUsuarioMatriculaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ComandoUsuarioMatriculaTestDataBuilder conEmail(String email) {
        this.email = email;
        return this;
    }

    public ComandoUsuarioMatriculaTestDataBuilder conCiudad(String ciudad) {
        this.ciudad = ciudad;
        return this;
    }

    public ComandoUsuarioMatriculaTestDataBuilder conDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public ComandoUsuarioMatricula build() {
        return new ComandoUsuarioMatricula(id,numeroIdentificacion, nombre, email,ciudad,direccion);
    }
}
