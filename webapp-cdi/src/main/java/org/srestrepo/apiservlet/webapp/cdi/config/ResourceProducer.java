package org.srestrepo.apiservlet.webapp.cdi.config;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ApplicationScoped
public class ResourceProducer {

    @Resource(name = "jdbc/javaCourseDB")
    private DataSource dataSource;

    @Produces
    @RequestScoped
    @PostgreSQLConnection
    private Connection produceJdbcConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
