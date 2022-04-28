package com.ceiba.configuracion;

import com.ceiba.matricula.consulta.manejador.ManejadorActualizarEstadoMatricula;
import com.ceiba.matricula.cron.CronMatricula;
import com.ceiba.matricula.puerto.dao.DaoUsuarioMatricula;
import com.ceiba.matricula.puerto.repositorio.RepositorioMatricula;
import com.ceiba.matricula.puerto.repositorio.RepositorioUsuarioMatricula;
import com.ceiba.matricula.servicio.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearMatricula servicioCrearMatricula(RepositorioMatricula repositorioMatricula, DaoUsuarioMatricula daoUsuarioMatricula, ServicioCrearUsuarioMatricula servicioCrearUsuarioMatricula) {
        return new ServicioCrearMatricula(repositorioMatricula, daoUsuarioMatricula, servicioCrearUsuarioMatricula);
    }

    @Bean
    public ServicioEliminarMatricula servicioEliminarMatricula(RepositorioMatricula repositorioMatricula) {
        return new ServicioEliminarMatricula(repositorioMatricula);
    }

    @Bean
    public ServicioActualizarMatricula servicioActualizarMatricula(RepositorioMatricula repositorioMatricula) {
        return new ServicioActualizarMatricula(repositorioMatricula);
    }

    @Bean
    public ServicioActualizarEstadoMatricula servicioActualizarEstadoMatricula(RepositorioMatricula repositorioMatricula, RepositorioUsuarioMatricula repositorioUsuarioMatricula) {
        return new ServicioActualizarEstadoMatricula(repositorioMatricula, repositorioUsuarioMatricula);
    }

    @Bean
    public ServicioPagarMatricula servicioPagarMatricula(RepositorioMatricula repositorioMatricula) {
        return new ServicioPagarMatricula(repositorioMatricula);
    }

    @Bean
    public ServicioCrearUsuarioMatricula servicioCrearUsuarioMatricula(RepositorioUsuarioMatricula repositorioUsuarioMatricula, DaoUsuarioMatricula daoUsuarioMatricula) {
        return new ServicioCrearUsuarioMatricula(repositorioUsuarioMatricula, daoUsuarioMatricula);
    }

    @Bean
    public ServicioActualizarUsuarioMatricula servicioActualizarUsuarioMatricula(RepositorioUsuarioMatricula repositorioUsuarioMatricula) {
        return new ServicioActualizarUsuarioMatricula(repositorioUsuarioMatricula);
    }

}
