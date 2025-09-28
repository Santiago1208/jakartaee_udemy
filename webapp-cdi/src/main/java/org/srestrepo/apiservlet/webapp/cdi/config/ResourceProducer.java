package org.srestrepo.apiservlet.webapp.cdi.config;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@ApplicationScoped
public class ResourceProducer {

    @Resource(name = "jdbc/javaCourseDB")
    private DataSource dataSource;
    @Inject
    private transient Logger log;

    @Produces
    @RequestScoped
    @PostgreSQLConnection
    private Connection produceJdbcConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void closeConnection(@Disposes @PostgreSQLConnection Connection connection) throws SQLException {
        connection.close();
        log.info("Connection to PostgreSQL closed");
    }

    @Produces
    @Dependent
    private Logger produceLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
