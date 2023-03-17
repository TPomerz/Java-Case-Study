package com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Cart model for the Database
 */
@Entity
@Table(name = "cart")
public class EcommerceCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "cart_user")
    private EcommerceUser user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "cart_product")
    private EcommerceProduct product;

    public EcommerceCart() {
    }

    public EcommerceCart(Integer id, EcommerceUser user, EcommerceProduct product) {
        this.id = id;
        this.user = user;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EcommerceUser getUser() {
        return user;
    }

    public void setUser(EcommerceUser user) {
        this.user = user;
    }

    public EcommerceProduct getProduct() {
        return product;
    }

    public void setProduct(EcommerceProduct product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "EcommerceCart{" +
                "id=" + id +
                ", user=" + user.toString() +
                ", product=" + product.toString() +
                '}';
    }
}
