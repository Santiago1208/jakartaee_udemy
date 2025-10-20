package org.srestrepo.webapp.jsf3.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Size(min = 4, max = 10, message = "SKU min value is 4 and max is 10")
    private String sku;

    @NotNull
    @Min(5)
    @Max(100_000)
    private Integer price;

    @NotNull
    @Column(name = "created_at")
    private LocalDate createdAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, String sku, Integer price) {
        this.name = name;
        this.sku = sku;
        this.price = price;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                '}';
    }
}
