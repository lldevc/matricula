package com.ceiba.matricula.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.matricula.modelo.entidad.Matricula;
import com.ceiba.matricula.puerto.repositorio.RepositorioMatricula;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioMatriculaH2 implements RepositorioMatricula {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="matricula", value="crearMatricula")
    private static String sqlCrear;

    @SqlStatement(namespace="matricula", value="actualizarMatricula")
    private static String sqlActualizarMatricula;

    @SqlStatement(namespace="matricula", value="eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace="matricula", value="existePorId")
    private static String sqlExistePorId;

    @SqlStatement(namespace="matricula", value="existePorIdUsuario")
    private static String sqlBuscarPorIdUsuario;

    @SqlStatement(namespace="matricula", value="buscarPorId")
    private static String buscarPorId;

    public RepositorioMatriculaH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Matricula matricula) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        paramSource.addValue("programaId", matricula.getPrograma().getId());
        paramSource.addValue("usuarioId", matricula.getUsuarioMatricula().getId());
        paramSource.addValue("estado", matricula.getEstadoDePago().toString());
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlCrear, paramSource,keyHolder,new String[] { "id" });
        return keyHolder.getKey().longValue();


    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

    @Override
    public void actualizar(Matricula matricula) {
        String estado = matricula.getEstadoDePago().toString();
        this.customNamedParameterJdbcTemplate.actualizarMatricula(matricula, sqlActualizarMatricula, estado);
    }

    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorId,paramSource, Boolean.class);
    }
}
