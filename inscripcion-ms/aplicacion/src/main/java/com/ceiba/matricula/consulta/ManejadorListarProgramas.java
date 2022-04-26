package com.ceiba.matricula.consulta;

import com.ceiba.matricula.modelo.dto.DtoPrograma;
import com.ceiba.matricula.puerto.dao.DaoPrograma;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarProgramas {

    private final DaoPrograma daoPrograma;

    public ManejadorListarProgramas(DaoPrograma daoPrograma){
        this.daoPrograma = daoPrograma;
    }

    public List<DtoPrograma> ejecutar(){ return this.daoPrograma.listar(); }
}
