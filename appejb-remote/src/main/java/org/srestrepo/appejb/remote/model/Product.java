package org.srestrepo.appejb.remote.model;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 42L;

    private String name;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                '}';
    }
}
