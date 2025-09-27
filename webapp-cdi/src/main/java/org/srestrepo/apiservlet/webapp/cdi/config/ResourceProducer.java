package org.srestrepo.apiservlet.webapp.cdi.config;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ResourceProducer {

    @Resource(name = "jdbc/javaCourseDB")
    private DataSource dataSource;

    @Produces
    @RequestScoped
    @Named("jdbcConnection")
    private Connection produceJdbcConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
