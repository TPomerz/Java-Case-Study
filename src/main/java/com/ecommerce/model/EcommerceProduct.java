package com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

/**
 * Product model for the Database
 */
@Entity
@Table(name = "products")
public class EcommerceProduct {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    private String name;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference(value = "cart_product")
    List<EcommerceCart> carts;

    public EcommerceProduct() {
    }

    public EcommerceProduct(int id, double price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public EcommerceProduct(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EcommerceProduct{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
