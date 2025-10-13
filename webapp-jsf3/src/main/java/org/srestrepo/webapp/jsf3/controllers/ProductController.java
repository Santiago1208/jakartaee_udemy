package org.srestrepo.webapp.jsf3.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import org.srestrepo.webapp.jsf3.entities.Product;

import java.util.Arrays;
import java.util.List;

@Model
public class ProductController {

    @Produces
    @Model
    public String title() {
        return "Hello World Jakarta Server Faces";
    }

    @Produces
    @RequestScoped
    @Named("products")
    public List<Product> getProducts() {
        return Arrays.asList(
                new Product("Peras"),
                new Product("Manzanas"),
                new Product("Mandarinas")
        );
    }
}
