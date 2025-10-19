package org.srestrepo.webapp.jsf3.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.srestrepo.webapp.jsf3.entities.Category;
import org.srestrepo.webapp.jsf3.entities.Product;
import org.srestrepo.webapp.jsf3.services.ProductService;

import java.util.List;
import java.util.ResourceBundle;

@Model
public class ProductController {

    @Inject
    private ProductService productService;

    @Inject
    private FacesContext facesContext;

    @Inject
    @Named("multiLanguage")
    private ResourceBundle resourceBundle;

    private Product product;

    private Long id;

    @Produces
    @Model
    public String title() {
        return resourceBundle.getString("product.text.title");
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
        if (product.getId() != null && product.getId() > 0) {
            facesContext.addMessage(null,
                    new FacesMessage(String.format(resourceBundle.getString("product.text.message.edit"), product.getName())));
        } else {
            facesContext.addMessage(null,
                    new FacesMessage(String.format(resourceBundle.getString("product.text.message.new"), product.getName())));
        }
        return "index.xhtml?faces-redirect=true";
    }

    public String edit(Long id) {
        this.id = id;
        return "product-form.xhtml";
    }

    public String delete(Product product) {
        productService.deleteProduct(product.getId());
        facesContext.addMessage(null,
                new FacesMessage(String.format(resourceBundle.getString("product.text.message.delete"), product.getName())));
        return "index.xhtml?faces-redirect=true";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
