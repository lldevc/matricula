package com.ceiba.matricula.consulta;

import com.ceiba.matricula.modelo.dto.DtoMatricula;
import com.ceiba.matricula.puerto.dao.DaoMatricula;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarMatricula {

    private final DaoMatricula daoMatricula;

    public ManejadorListarMatricula(DaoMatricula daoMatricula){
        this.daoMatricula = daoMatricula;
    }

    public List<DtoMatricula> ejecutar(){
        return daoMatricula.listarMatriculas();
    }

    public DtoMatricula ejecutarListarPorId(Long id){
        return daoMatricula.buscarMatricula(id);
    }

    public List<DtoMatricula> ejecutarListarPorIdUsuario(Long id){
        return this.daoMatricula.buscarMatriculasPorIdUsuario(id);
    }

    public List<DtoMatricula> ejecutarListarPorNumeroIdentificacionUsuario(Long numeroIdentificacion){
        return this.daoMatricula.buscarMatriculasPorNumeroIdentificacionUsuario(numeroIdentificacion);
    }
}