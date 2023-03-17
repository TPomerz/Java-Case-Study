package com.ecommerce.service;

import com.ecommerce.model.EcommerceUser;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Business logic necessary for handling requests related to authentication and registration
 */
@Service
public class EcommerceUserAuthService {

    private final EcommerceUserService uService;

    public EcommerceUserAuthService(EcommerceUserService uService) {this.uService = uService;}

    /**
     * Compares the provided email and password with data stored in the database
     * @param email Attempted login email
     * @param password Attempted login password
     * @return Optional object containing a user if successful otherwise null
     */
    public Optional<EcommerceUser> authenticateUser(String email, String password) {
        Optional<EcommerceUser> user = uService.getUser(email);
        if(user.isPresent() && user.get().getPassword() == password) {
            return user;
        } else {
            return Optional.empty();
        }
    }

    /**
     * Sends a new user to be persisted in the database
     * @param user new user being registered
     * @return user that was created
     */
    public EcommerceUser register(EcommerceUser user) {return uService.save(user);}
}
