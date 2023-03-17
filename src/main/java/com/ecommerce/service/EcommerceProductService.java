package com.ecommerce.service;

import com.ecommerce.dao.EcommerceDAOCart;
import com.ecommerce.dao.EcommerceDAOProductInterface;
import com.ecommerce.model.EcommerceCart;
import com.ecommerce.model.EcommerceProduct;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Product Service that handles communication between controllers and DAOs
 */
@Service
public class EcommerceProductService {

    private static int cartId;

    private static boolean ispresent;
    private final EcommerceDAOProductInterface productDAO;
    private final EcommerceDAOCart cartDAO;

    /**
     * Constructor for instantiating Product Services
     * @param productDAO - Handles SQL queries for the Product Table
     * @param cartDAO - Handles SQL queries for the Cart Table
     */
    public EcommerceProductService(EcommerceDAOProductInterface productDAO, EcommerceDAOCart cartDAO) {
        this.productDAO = productDAO;
        this.cartDAO = cartDAO;
    }


    /**
     * Queries the database for a list of all products
     * @return List containing all products in the database
     */
    public List<EcommerceProduct> findAll(){return productDAO.findAll(Sort.by("id"));}

    /**
     * Queries the database for a specific product identified by ID
     * @param id Product ID
     * @return Product based on ID or null
     */
    public Optional<EcommerceProduct> findById(int id){return productDAO.findById(id);}

    /**
     * Queries the database for a specific cart identified by ID
     * @param id Cart ID
     * @return Cart based on ID or null
     */
    public List<EcommerceCart> getCart(int id) {return cartDAO.getCart(id);}

    /**
     * Submits a SQL statement to add a cart to the database
     * @param cart Cart Object
     * @return Whether the save was successful or not
     */
    public EcommerceCart addCart(EcommerceCart cart) {return cartDAO.save(cart);}

    /**
     * Deletes a cart from the database based on ID
     * @param id ID of the Cart
     */
    public void clearCart(Integer id) {cartDAO.clearCart(id);}

    /**
     * Checks if a cart contains a certain product
     * @param userId User ID of the cart object
     * @param prodId Product ID that is being checked
     * @return True/False if contents are matched
     */
    public boolean inCart(Integer userId, Integer prodId){
        ispresent = false;
        cartDAO.findAll().forEach(element ->{
            if(element.getUser().getId() == userId && element.getProduct().getId() == prodId){
                ispresent = true;
            }
        });
        return ispresent;
    }

    public int findCart(Integer userId, Integer prodId){
        cartId = -1;
        cartDAO.findAll().forEach(element ->{
            if(element.getUser().getId() == userId && element.getProduct().getId() == prodId){
                cartId = element.getId();
            }
        });
        return cartId;
    }
}

