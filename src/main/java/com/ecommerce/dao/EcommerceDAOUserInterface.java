package com.ecommerce.dao;

import com.ecommerce.model.EcommerceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EcommerceDAOUserInterface extends JpaRepository<EcommerceUser, Integer> {
    Optional<EcommerceUser> findByEmailAndPassword(String email, String password);

    Optional<EcommerceUser> findByEmail(String email);
}
