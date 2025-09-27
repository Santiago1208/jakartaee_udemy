package org.srestrepo.apiservlet.webapp.cdi.config;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import org.srestrepo.apiservlet.webapp.cdi.util.DatasourceJdbcConnection;

import java.sql.Connection;

public class ResourceProducer {

    @Produces
    @RequestScoped
    @Named("jdbcConnection")
    private Connection produceJdbcConnection() {
        return DatasourceJdbcConnection.getConnection();
    }
}
