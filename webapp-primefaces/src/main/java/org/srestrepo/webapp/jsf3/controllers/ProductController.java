package org.srestrepo.webapp.jsf3.controllers;

import jakarta.annotation.PostConstruct;
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

    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = productService.getProducts();
    }

    @Produces
    @Model
    public String title() {
        return resourceBundle.getString("product.text.title");
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
        if (product.getId() != null && product.getId() > 0) {
            facesContext.addMessage(null,
                    new FacesMessage(String.format(resourceBundle.getString("product.text.message.edit"), product.getName())));
        } else {
            facesContext.addMessage(null,
                    new FacesMessage(String.format(resourceBundle.getString("product.text.message.new"), product.getName())));
        }
        productService.saveProduct(product);
        products = productService.getProducts();
        return "index.xhtml";
    }

    public String edit(Long id) {
        this.id = id;
        return "product-form.xhtml";
    }

    public void delete(Product product) {
        productService.deleteProduct(product.getId());
        facesContext.addMessage(null,
                new FacesMessage(String.format(resourceBundle.getString("product.text.message.delete"), product.getName())));
        products = productService.getProducts();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
