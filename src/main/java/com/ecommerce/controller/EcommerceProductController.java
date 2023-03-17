package com.ecommerce.controller;

import com.ecommerce.model.EcommerceCart;
import com.ecommerce.model.EcommerceProduct;
import com.ecommerce.service.EcommerceProductService;
import com.ecommerce.service.EcommerceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * Controller for handling requested related to Products using "/product"
 */
@RestController
@RequestMapping("/product")
public class EcommerceProductController {

    private final EcommerceProductService productService;

    private final EcommerceUserService userService;

    @Autowired
    public EcommerceProductController(EcommerceProductService productService, EcommerceUserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    /**
     * Returns a complete list of all objects in the database
     * @return List of all products
     */
    @GetMapping
    public ResponseEntity<List<EcommerceProduct>> getInventory(){return ResponseEntity.ok(productService.findAll());}

    /**
     * Returns a single product with a matching ID
     * @param id Product ID
     * @return A product containing the matching ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<EcommerceProduct> getProductById(@PathVariable("id") int id) {
        Optional<EcommerceProduct> optional = productService.findById(id);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    /**
     * Return a cart with a matching User ID
     * @param userId User ID
     * @return List of carts
     */
    @GetMapping("/cart")
    public List<EcommerceCart> getCart(@RequestParam int userId){return productService.getCart(userId);}

//    @PostMapping("/cart")
//    public EcommerceCart addCart(@RequestParam int userId, @RequestParam int prodId){
//        boolean ispresent = productService.inCart(userId, prodId);
//        if(ispresent == false) {
//            EcommerceCartDTO cart = new EcommerceCartDTO(userId, prodId, userService, productService);
//            EcommerceUser newUser = cart.getUser();
//            EcommerceProduct newProduct = cart.getProduct();
//            EcommerceCart temp = new EcommerceCart(null, newUser, newProduct);
//            EcommerceCart newCart = productService.addCart(temp);
//            return newCart;
//        }
//        int cartId = productService.findCart(userId, prodId);
//        return productService.add
//    }
}
