package com.ceiba.matricula.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.matricula.modelo.dto.DtoMatricula;
import com.ceiba.matricula.puerto.dao.DaoMatricula;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoMatriculaH2 implements DaoMatricula {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="matricula", value="listar")
    private static String sqlListar;

    @SqlStatement(namespace="matricula", value="buscarPorId")
    private static String sqlBuscarPorId;

    @SqlStatement(namespace="matricula", value="buscarPorIdUsuario")
    private static String sqlBuscarPorIdUsiario;

    @SqlStatement(namespace="matricula", value="buscarPorNumeroIdentificacionUsuario")
    private static String sqlBuscarPorNumeroIdentificacionUsiario;

    public DaoMatriculaH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoMatricula> listarMatriculas() {

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoMatricula());
    }

    @Override
    public DtoMatricula buscarMatricula(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlBuscarPorId, paramSource, new MapeoMatricula());
    }

    @Override
    public List<DtoMatricula> buscarMatriculasPorIdUsuario(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlBuscarPorIdUsiario, paramSource, new MapeoMatricula());
    }

    @Override
    public List<DtoMatricula> buscarMatriculasPorNumeroIdentificacionUsuario(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlBuscarPorNumeroIdentificacionUsiario, paramSource, new MapeoMatricula());
    }
}
