package com.ceiba.matricula.consulta;

import com.ceiba.matricula.modelo.dto.DtoUsuarioMatricula;
import com.ceiba.matricula.puerto.dao.DaoUsuarioMatricula;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarUsuariosMatricula {

    private final DaoUsuarioMatricula daoUsuarioMatricula;

    public ManejadorListarUsuariosMatricula(DaoUsuarioMatricula daoUsuarioMatricula){
        this.daoUsuarioMatricula = daoUsuarioMatricula;
    }

    public List<DtoUsuarioMatricula> ejecutar(){ return this.daoUsuarioMatricula.listar(); }

    public DtoUsuarioMatricula ejecutar(Long id){ return this.daoUsuarioMatricula.listarPorId(id); }
}
