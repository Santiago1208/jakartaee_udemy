package org.srestrepo.apiservlet.webapp.jstl.services;

public class JdbcServiceException extends RuntimeException {

    public JdbcServiceException(String message) {
        super(message);
    }

    public JdbcServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
