package com.ceiba.matricula.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.matricula.modelo.dto.DtoPrograma;
import com.ceiba.matricula.puerto.dao.DaoPrograma;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoProgramaH2 implements DaoPrograma {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="programa", value="listarPrograma")
    private static String sqlListarPrograma;

    public DaoProgramaH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoPrograma> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarPrograma, new MapeoPrograma());
    }
}
