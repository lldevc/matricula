package com.ceiba.matricula.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.matricula.modelo.entidad.Matricula;
import com.ceiba.matricula.puerto.repositorio.RepositorioMatricula;

public class ServicioActualizarMatricula {

    private static final String LA_MATRICULA_NO_EXISTE_EN_EL_SISTEMA = "La matricula no existe en el sistema";

    private final RepositorioMatricula repositorioMatricula;

    public ServicioActualizarMatricula(RepositorioMatricula repositorioMatricula) {
        this.repositorioMatricula = repositorioMatricula;
    }

    public void ejecutar(Matricula matricula) {
        validarExistenciaPrevia(matricula);
        this.repositorioMatricula.actualizar(matricula);
    }

    private void validarExistenciaPrevia(Matricula matricula) {
        boolean existe = this.repositorioMatricula.existePorId(matricula.getId());
        if(!existe) {
            throw new ExcepcionDuplicidad(LA_MATRICULA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
