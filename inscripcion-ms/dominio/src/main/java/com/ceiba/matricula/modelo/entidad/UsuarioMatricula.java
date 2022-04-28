package com.ceiba.matricula.modelo.entidad;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
@Setter
public class UsuarioMatricula {

    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO = "Se debe ingresar el nombre de usuario";
    private static final String SE_DEBE_INGRESAR_EL_NUMERO_DE_IDENTIFICACION_DE_USUARIO = "Se debe ingresar el numero de identificaion de usuario";
    private static final String SE_DEBE_INGRESAR_EL_EMAIL_DE_USUARIO = "Se debe ingresar el email de usuario";
    private static final String SE_DEBE_INGRESAR_EL_CIUDAD_DE_USUARIO = "Se debe ingresar el ciudad de usuario";
    private static final String SE_DEBE_INGRESAR_EL_DIRECCION_DE_USUARIO = "Se debe ingresar el direccion de usuario";
    private static final String EMAIL_INGRESADO_INVALIDO = "El email ingresado es invalido";

    private static final int LONGITUD_MINIMA_CLAVE = 4;

    private Long id;
    private Long numeroIdentificacion;
    private String nombre;
    private String email;
    private String ciudad;
    private String direccion;
    private LocalDateTime fechaSancion;

    public UsuarioMatricula() {
    }

    public UsuarioMatricula(Long id, Long numeroIdentificacion, String nombre, String email, String ciudad, String direccion) {
        validarObligatorio(numeroIdentificacion, SE_DEBE_INGRESAR_EL_NUMERO_DE_IDENTIFICACION_DE_USUARIO);
        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO);
        validarObligatorio(email, SE_DEBE_INGRESAR_EL_EMAIL_DE_USUARIO);
        validarRegex(email, "([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)", EMAIL_INGRESADO_INVALIDO);

        this.id = id;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombre = nombre;
        this.email = email;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.fechaSancion = null;
    }

}
