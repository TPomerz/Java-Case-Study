package com.ecommerce.dto;

import com.ecommerce.model.EcommerceProduct;
import com.ecommerce.model.EcommerceUser;
import com.ecommerce.service.EcommerceProductService;
import com.ecommerce.service.EcommerceUserService;

public class EcommerceCartDTO {

    private Integer user_id;
    private Integer product_id;
    private EcommerceUserService userService;
    private EcommerceProductService productService;

    public EcommerceCartDTO(Integer userid, Integer productid, EcommerceUserService userService, EcommerceProductService productService){
        this.user_id = userid;
        this.product_id = productid;
        this.userService = userService;
        this.productService = productService;
    }

    public EcommerceUser getUser(){
        return userService.getUser(user_id);
    }
    public EcommerceProduct getProduct(){
        return productService.findById(product_id).get();
    }
}
