package com.ecommerce.service;

import com.ecommerce.dao.EcommerceDAOUserInterface;
import com.ecommerce.model.EcommerceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EcommerceUserService {
    private EcommerceDAOUserInterface userDAO;

    @Autowired
    public EcommerceUserService(EcommerceDAOUserInterface userDAO) {this.userDAO = userDAO;}


    public EcommerceUser save(EcommerceUser user) {return userDAO.save(user);}

    public EcommerceUser getUser(Integer userId) {return userDAO.findByUserId(userId);}

    public Optional<EcommerceUser> getUser(String email) {return userDAO.findByEmail(email);}

    /**
     * Find a user in the database with a matchin user ID
     * @param userId ID of the user object
     * @return The user object if a matching ID is found
     */
    public Optional<EcommerceUser> findById(int userId) {
        Optional<EcommerceUser> optional = userDAO.findById(userId);
        if(optional.isPresent()) {
            EcommerceUser user = optional.get();
            return Optional.of(user);
        }
        return optional;
    }
}
