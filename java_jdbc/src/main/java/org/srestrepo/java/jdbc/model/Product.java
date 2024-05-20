package org.srestrepo.java.jdbc.model;

import java.util.Date;

public class Product {
    private Long id;
    private String name;
    private Integer price;
    private Date registerDate;

    public Product() {
    }

    public Product(Long id, String name, Integer price, Date registerDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.registerDate = registerDate;
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

    @Override
    public String toString() {
        return id +
                " | " + name +
                " | " + price +
                " | " + registerDate;
    }
}
