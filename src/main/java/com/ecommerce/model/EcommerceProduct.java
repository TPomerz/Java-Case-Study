package com.ecommerce.model;

import jakarta.persistence.*;


public class EcommerceProduct {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    private String name;

    public EcommerceProduct(int id) {
        this.id = id;
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
