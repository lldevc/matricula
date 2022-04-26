package com.ceiba.matricula.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.matricula.modelo.dto.DtoMatricula;
import com.ceiba.matricula.modelo.entidad.EstadoDePago;
import com.ceiba.matricula.modelo.entidad.Programa;
import com.ceiba.matricula.modelo.entidad.UsuarioMatricula;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class MapeoMatricula implements RowMapper<DtoMatricula>, MapperResult {

    @Override
    public DtoMatricula mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Map<String, EstadoDePago> mapingEstados = new HashMap<>();
        mapingEstados.put(EstadoDePago.PENDIENTE.toString(), EstadoDePago.PENDIENTE);
        mapingEstados.put(EstadoDePago.PAGADA.toString(), EstadoDePago.PAGADA);
        mapingEstados.put(EstadoDePago.VENCIDA.toString(), EstadoDePago.VENCIDA);

        Long id_programa = resultSet.getLong("id_programa");
        String nombre_programa = resultSet.getString("nombre_programa");
        Double precio_programa = resultSet.getDouble("precio_programa");
        Double recargo_programa = resultSet.getDouble("recargo_programa");
        Integer diasParaRecargo_programa = resultSet.getInt("dias_para_recargo_programa");
        Programa programa = new Programa(id_programa, nombre_programa, precio_programa, recargo_programa, diasParaRecargo_programa);


        Long id_usuario = resultSet.getLong("id_usuario");
        Long numeroIdentificacion_usuario = resultSet.getLong("numero_identificacion");
        String nombre_usuario = resultSet.getString("nombre_usuario");
        String email_usuario = resultSet.getString("email_usuario");
        String ciudad_usuario = resultSet.getString("ciudad_usuario");
        String direccion_usuario = resultSet.getString("direccion_usuario");
        UsuarioMatricula usuarioMatricula = new UsuarioMatricula(id_usuario, numeroIdentificacion_usuario, nombre_usuario, email_usuario, ciudad_usuario, direccion_usuario);



        Long id = resultSet.getLong("id");
        Double valor = resultSet.getDouble( "valor");
        boolean recargo = resultSet.getBoolean("recargo");
        LocalDateTime fechaCreacion = resultSet.getTimestamp("fecha_creacion").toLocalDateTime();
        LocalDateTime fechaLimitePagoSinRecargo = resultSet.getTimestamp("fecha_limite_pago_sin_recargo").toLocalDateTime();
        LocalDateTime fechaMaximaPago = resultSet.getTimestamp("fecha_maxima_pago").toLocalDateTime();
        String estadoDePagoBd = resultSet.getString("estado");

        EstadoDePago estadoDePago = null;
        Optional<Map<String, EstadoDePago>> optionalEstadoDePago = Stream.of(mapingEstados).filter(e -> e.containsKey(estadoDePagoBd)).findFirst();
        if (optionalEstadoDePago.isPresent()){
            estadoDePago = optionalEstadoDePago.get().get(estadoDePagoBd);
        }

        return new DtoMatricula(id, valor, recargo, estadoDePago, fechaCreacion,fechaLimitePagoSinRecargo, fechaMaximaPago, programa, usuarioMatricula);
    }

}
