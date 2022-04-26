package com.ceiba.dominio.excepcion;

public class ExcepcionUsuarioSancionado extends RuntimeException {

	private static final long serialVersionUID = 1L;

    public ExcepcionUsuarioSancionado(String message) {
        super(message);
    }
}
