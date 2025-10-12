package org.srestrepo.webapp.ejb.service;

import jakarta.ejb.Stateless;

@Stateless
public class ServiceEJB {
    public String greeting(String name) {
        System.out.println("Printing from inside ServiceEJB: " + this);
        return "Hello " + name;
    }
}
