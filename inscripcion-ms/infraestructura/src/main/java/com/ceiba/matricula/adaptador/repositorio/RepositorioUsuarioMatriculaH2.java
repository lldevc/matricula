package com.ceiba.matricula.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;
import com.ceiba.matricula.puerto.repositorio.RepositorioUsuarioMatricula;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioUsuarioMatriculaH2 implements RepositorioUsuarioMatricula {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="usuarioMatricula", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="usuarioMatricula", value="actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace="usuarioMatricula", value="eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace="usuarioMatricula", value="existe")
    private static String sqlExiste;

    @SqlStatement(namespace="usuarioMatricula", value="existePorId")
    private static String sqlExistePorId;

    @SqlStatement(namespace="usuarioMatricula", value="existeNumeroIdentificacion")
    private static String sqlExistePorNumeroIdentificacion;

    @SqlStatement(namespace="usuarioMatricula", value="tieneMatricula")
    private static String sqlTieneMatricula;

    public RepositorioUsuarioMatriculaH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(UsuarioMatricula usuarioMatricula) {
        return this.customNamedParameterJdbcTemplate.crear(usuarioMatricula, sqlCrear);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

    @Override
    public boolean existe(String nombre) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("nombre", nombre);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);
    }

    @Override
    public void actualizar(UsuarioMatricula usuarioMatricula) {
        this.customNamedParameterJdbcTemplate.actualizar(usuarioMatricula, sqlActualizar);
    }

    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorId,paramSource, Boolean.class);
    }

    @Override
    public boolean existePorNumeroIdentificacion(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorNumeroIdentificacion,paramSource, Boolean.class);
    }

    @Override
    public boolean tieneMatricula(Long id, String nombre) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        paramSource.addValue("nombre", nombre);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlTieneMatricula,paramSource, Boolean.class);
    }
}
