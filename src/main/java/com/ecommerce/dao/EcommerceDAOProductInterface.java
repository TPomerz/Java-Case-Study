package com.ecommerce.dao;

import com.ecommerce.model.EcommerceProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EcommerceDAOProductInterface extends JpaRepository<EcommerceProduct, Integer> {

}

