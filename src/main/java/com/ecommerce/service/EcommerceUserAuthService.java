package com.ecommerce.service;

import com.ecommerce.model.EcommerceUser;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

public class EcommerceUserAuthService {

    private final EcommerceUserService uService;

    public EcommerceUserAuthService(EcommerceUserService uService) {this.uService = uService;}

    public Optional<EcommerceUser> authenticateUser(String email, String password) {
        Optional<EcommerceUser> user = uService.getUser(email);
        if(user.isPresent() && user.get().getPassword() == password) {
            return user;
        } else {
            return Optional.empty();
        }
    }

    public EcommerceUser register(EcommerceUser user) {return uService.save(user);}
}
