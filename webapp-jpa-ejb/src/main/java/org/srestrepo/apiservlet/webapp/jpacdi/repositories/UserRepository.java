package org.srestrepo.apiservlet.webapp.jpacdi.repositories;

import org.srestrepo.apiservlet.webapp.jpacdi.models.entities.User;

public interface UserRepository extends CrudRepository<User> {
    User findByUsername(String username) throws Exception;
    User findByEmail(String email) throws Exception;
}
