package org.srestrepo.apiservlet.webapp.jdbc.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.jdbc.services.LoginService;
import org.srestrepo.apiservlet.webapp.jdbc.services.LoginSessionService;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/cart/*"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LoginService loginService = new LoginSessionService();
        Optional<String> username = loginService.getUsername((HttpServletRequest) servletRequest);
        if (username.isPresent()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sorry, you are not allowed to access this page");
        }
    }
}
