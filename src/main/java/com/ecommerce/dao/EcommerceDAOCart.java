package com.ecommerce.dao;

import com.ecommerce.model.EcommerceCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EcommerceDAOCart extends JpaRepository<EcommerceCart, Integer> {
    @Query(value = "SELECT * FROM cart WHERE user_id = :userId", nativeQuery = true)
    List<EcommerceCart> getCart(@Param("userId") Integer userId);

    @Modifying
    @Query(value = "DELETE FROM cart WHERE user_id = :userId", nativeQuery = true)
    void clearCart(@Param("userId") Integer userId);
}
