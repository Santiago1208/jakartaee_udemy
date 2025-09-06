package org.srestrepo.apiservlet.webapp.authjdbc.services;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface LoginService {

    Optional<String> getUsername(HttpServletRequest request);

}
