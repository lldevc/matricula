package com.ceiba.matricula.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.matricula.modelo.dto.DtoPrograma;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoPrograma implements RowMapper<DtoPrograma>, MapperResult {

    @Override
    public DtoPrograma mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        Double precio = resultSet.getDouble("precio");
        Double recargo = resultSet.getDouble("recargo");
        Integer diasParaRecargo = resultSet.getInt( "dias_para_recargo");

        return new DtoPrograma(id,nombre,precio,recargo, diasParaRecargo);
    }

}
