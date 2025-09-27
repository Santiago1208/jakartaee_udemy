package org.srestrepo.apiservlet.webapp.cdi.filters;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.cdi.services.JdbcServiceException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter({"/*"})
public class JdbcConnectionFilter implements Filter {

    @Inject
    @Named("jdbcConnection")
    private Connection connection;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try (Connection connection = this.connection) {
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            try {
                servletRequest.setAttribute("jdbcConnection", connection);
                filterChain.doFilter(servletRequest, servletResponse);
                connection.commit();
            } catch (SQLException | JdbcServiceException e) {
                connection.rollback();
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
