package org.srestrepo.webapp.jsf3.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.srestrepo.webapp.jsf3.entities.Product;
import org.srestrepo.webapp.jsf3.services.ProductService;

import java.util.List;

@Model
public class ProductController {

    @Inject
    private ProductService productService;

    private Product product;

    @Produces
    @Model
    public String title() {
        return "Hello World Jakarta Server Faces";
    }

    @Produces
    @RequestScoped
    @Named("products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @Produces
    @Model
    public Product product() {
        this.product = new Product();
        return product;
    }

    public String save() {
        System.out.println(product);
        // productService.save(product);
        return "index.xhtml?faces-redirect=true";
    }
}
