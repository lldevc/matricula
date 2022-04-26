package com.ceiba.matricula.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.matricula.modelo.dto.DtoUsuarioMatricula;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class MapeoUsuarioMatricula implements RowMapper<DtoUsuarioMatricula>, MapperResult {

    @Override
    public DtoUsuarioMatricula mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        Long numeroIdentificacion = resultSet.getLong("numero_identificacion");
        String nombre = resultSet.getString("nombre");
        String email = resultSet.getString("email");
        String ciudad = resultSet.getString("ciudad");
        String direccion = resultSet.getString("direccion");

        Timestamp timestamp = resultSet.getTimestamp("fecha_sancion");
        LocalDateTime fechaSancion = null;
        if (timestamp != null){
            fechaSancion = timestamp.toLocalDateTime();
        }

        return new DtoUsuarioMatricula(id,numeroIdentificacion,nombre,email,ciudad,direccion,fechaSancion);
    }

}
