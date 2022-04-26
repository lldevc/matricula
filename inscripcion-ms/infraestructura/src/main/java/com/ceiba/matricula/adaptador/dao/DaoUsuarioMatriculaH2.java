package com.ceiba.matricula.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.matricula.modelo.dto.DtoUsuarioMatricula;
import com.ceiba.matricula.puerto.dao.DaoUsuarioMatricula;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoUsuarioMatriculaH2 implements DaoUsuarioMatricula {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="usuarioMatricula", value="listar")
    private static String sqlListar;

    @SqlStatement(namespace="usuarioMatricula", value="buscarPorId")
    private static String sqlBuscarPorId;

    @SqlStatement(namespace="usuarioMatricula", value="buscarPorNumeroIdentificacion")
    private static String sqlBuscarPorNumeroIdentificacion;

    public DaoUsuarioMatriculaH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoUsuarioMatricula> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoUsuarioMatricula());
    }

    @Override
    public DtoUsuarioMatricula listarPorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlBuscarPorId, paramSource,new MapeoUsuarioMatricula());
    }

    @Override
    public DtoUsuarioMatricula listarPorNumeroIdentificacion(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlBuscarPorNumeroIdentificacion, paramSource,new MapeoUsuarioMatricula());
    }
}
