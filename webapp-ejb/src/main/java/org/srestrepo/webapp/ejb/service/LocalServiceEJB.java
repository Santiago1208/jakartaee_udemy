package org.srestrepo.webapp.ejb.service;

import jakarta.ejb.Local;

@Local
public interface LocalServiceEJB {
    String greeting(String name);

}
