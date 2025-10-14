package org.srestrepo.webapp.jsf3.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.srestrepo.webapp.jsf3.entities.Category;
import org.srestrepo.webapp.jsf3.entities.Product;
import org.srestrepo.webapp.jsf3.services.ProductService;

import java.util.List;

@Model
public class ProductController {

    @Inject
    private ProductService productService;

    private Product product;

    private Long id;

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
        if (id != null && id > 0) {
            productService.getProduct(id).ifPresent(p -> this.product = p);
        }
        return product;
    }

    @Produces
    @Model
    public List<Category> categories() {
        return productService.getCategories();
    }

    public String save() {
        System.out.println(product);
        productService.saveProduct(product);
        return "index.xhtml?faces-redirect=true";
    }

    public String edit(Long id) {
        this.id = id;
        return "product-form.xhtml";
    }

    public String delete(Long id) {
        productService.deleteProduct(id);
        return "index.xhtml?faces-redirect=true";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
