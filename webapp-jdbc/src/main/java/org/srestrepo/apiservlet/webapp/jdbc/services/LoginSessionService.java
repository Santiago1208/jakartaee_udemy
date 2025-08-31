package org.srestrepo.apiservlet.webapp.jdbc.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginSessionService implements LoginService {

    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return Optional.ofNullable((String) session.getAttribute("username"));
    }
}
