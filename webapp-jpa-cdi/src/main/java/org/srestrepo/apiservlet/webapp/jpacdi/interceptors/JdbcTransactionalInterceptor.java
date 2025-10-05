package org.srestrepo.apiservlet.webapp.jpacdi.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.srestrepo.apiservlet.webapp.jpacdi.config.PostgreSQLConnection;
import org.srestrepo.apiservlet.webapp.jpacdi.services.JdbcServiceException;

import java.sql.Connection;
import java.util.logging.Logger;

@JdbcTransactional
@Interceptor
public class JdbcTransactionalInterceptor {

    @Inject
    @PostgreSQLConnection
    private Connection connection;
    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext invocation) throws Throwable {
        try {
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }

            log.info("-------> JDBC Transaction Begins " + invocation.getMethod().getName() + " of " + invocation.getMethod().getDeclaringClass().getName());
            Object result = invocation.proceed();
            connection.commit();
            log.info("-------> JDBC Transaction Ended " + invocation.getMethod().getName() + " of " + invocation.getMethod().getDeclaringClass().getName());

            return result;
        } catch (JdbcServiceException e) {
            connection.rollback();
            throw e;
        }
    }
}
