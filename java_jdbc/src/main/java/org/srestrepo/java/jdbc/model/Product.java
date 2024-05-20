package org.srestrepo.java.jdbc.model;

import java.util.Date;

public class Product {
    private Long id;
    private String name;
    private Integer price;
    private Date registerDate;
    private String sku;
    private Category category;

    public Product() {
    }

    public Product(Long id, String name, Integer price, Date registerDate, String sku) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.registerDate = registerDate;
        this.sku = sku;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return id +
                " | " + name +
                " | " + price +
                " | " + registerDate +
                " | " + sku +
                " | " + category.toString();
    }
}
