package org.srestrepo.apiservlet.webapp.authjdbc.repositories;

import org.srestrepo.apiservlet.webapp.authjdbc.models.User;

import java.sql.SQLException;

public interface UserJdbcRepository extends JdbcRepository<User> {
    User findByUsername(String username) throws SQLException;
    User findByEmail(String email) throws SQLException;
}
