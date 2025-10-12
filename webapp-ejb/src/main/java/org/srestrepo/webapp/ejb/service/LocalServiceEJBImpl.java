package org.srestrepo.webapp.ejb.service;

import jakarta.ejb.Stateless;

@Stateless
public class LocalServiceEJBImpl implements LocalServiceEJB {

    private int counter;

    @Override
    public String greeting(String name) {
        System.out.println("Printing from inside ServiceEJB: " + this);

        counter++;
        System.out.println("Counter: " + counter);

        return "Hello " + name;
    }
}
