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

    private String nameSearch;

    @PostConstruct
    public void init() {
        this.products = productService.getProducts();
        product = new Product();
    }

    @Produces
    @Model
    public String title() {
        return resourceBundle.getString("product.text.title");
    }

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

    public void save() {
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
        product = new Product();
    }

    public void edit(Long id) {
        this.id = id;
        product();
    }

    public void delete(Product product) {
        productService.deleteProduct(product.getId());
        facesContext.addMessage(null,
                new FacesMessage(String.format(resourceBundle.getString("product.text.message.delete"), product.getName())));
        products = productService.getProducts();
    }

    public void search() {
        this.products = productService.searchByName(this.nameSearch);
    }

    public void onCloseDialog() {
        System.out.println("Closing Add/Edit Dialog");
        product = new Product();
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

    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
