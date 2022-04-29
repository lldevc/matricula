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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class MapeoMatricula implements RowMapper<DtoMatricula>, MapperResult {

    @Override
    public DtoMatricula mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Map<String, EstadoDePago> mapingEstados = new HashMap<>();
        mapingEstados.put(EstadoDePago.PENDIENTE.toString(), EstadoDePago.PENDIENTE);
        mapingEstados.put(EstadoDePago.PAGADA.toString(), EstadoDePago.PAGADA);
        mapingEstados.put(EstadoDePago.VENCIDA.toString(), EstadoDePago.VENCIDA);

        Long idPrograma = resultSet.getLong("id_programa");
        String nombrePrograma = resultSet.getString("nombre_programa");
        Double precioPrograma = resultSet.getDouble("precio_programa");
        Double recargoPrograma = resultSet.getDouble("recargo_programa");
        Integer diasParaRecargoPrograma = resultSet.getInt("dias_para_recargo_programa");
        Programa programa = new Programa(idPrograma, nombrePrograma, precioPrograma, recargoPrograma, diasParaRecargoPrograma);


        Long idUsuario = resultSet.getLong("id_usuario");
        Long numeroIdentificacionUsuario = resultSet.getLong("numero_identificacion");
        String nombreUsuario = resultSet.getString("nombre_usuario");
        String emailUsuario = resultSet.getString("email_usuario");
        String ciudadUsuario = resultSet.getString("ciudad_usuario");
        String direccionUsuario = resultSet.getString("direccion_usuario");
        UsuarioMatricula usuarioMatricula = new UsuarioMatricula(idUsuario, numeroIdentificacionUsuario, nombreUsuario, emailUsuario, ciudadUsuario, direccionUsuario);



        Long id = resultSet.getLong("id");
        Double valor = resultSet.getDouble( "valor");
        boolean recargo = resultSet.getBoolean("recargo");
        LocalDateTime fechaCreacion = resultSet.getTimestamp("fecha_creacion").toLocalDateTime();
        LocalDateTime fechaLimitePagoSinRecargo = resultSet.getTimestamp("fecha_limite_pago_sin_recargo").toLocalDateTime();
        LocalDateTime fechaMaximaPago = resultSet.getTimestamp("fecha_maxima_pago").toLocalDateTime();
        String estadoDePagoBd = resultSet.getString("estado");

        Optional<Map<String, EstadoDePago>> optionalEstadoDePago = Stream.of(mapingEstados).filter(e -> e.containsKey(estadoDePagoBd)).findFirst();
        EstadoDePago estadoDePago = Objects.requireNonNull(optionalEstadoDePago.get().get(estadoDePagoBd));


        return new DtoMatricula(id, valor, recargo, estadoDePago, fechaCreacion,fechaLimitePagoSinRecargo, fechaMaximaPago, programa, usuarioMatricula);
    }

}
