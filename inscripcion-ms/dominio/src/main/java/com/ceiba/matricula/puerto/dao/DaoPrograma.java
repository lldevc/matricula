package com.ceiba.matricula.puerto.dao;

import com.ceiba.matricula.modelo.dto.DtoPrograma;

import java.util.List;

public interface DaoPrograma {

    /**
     * Permite listar los programas
     * @return los programas
     */
    List<DtoPrograma> listar();
}
