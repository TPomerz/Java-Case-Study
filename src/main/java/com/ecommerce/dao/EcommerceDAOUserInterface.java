package com.ecommerce.dao;

import com.ecommerce.model.EcommerceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EcommerceDAOUserInterface extends JpaRepository<EcommerceUser, Integer> {
    Optional<EcommerceUser> findByEmailAndPassword(String email, String password);

    Optional<EcommerceUser> findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE id = :userId", nativeQuery = true)
    EcommerceUser findByUserId(@Param("userId") Integer userId);
}
