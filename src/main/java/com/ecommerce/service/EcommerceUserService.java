package com.ecommerce.service;

import com.ecommerce.dao.EcommerceDAOUserInterface;
import com.ecommerce.model.EcommerceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EcommerceUserService {
    private EcommerceDAOUserInterface userDAO;

    @Autowired public EcommerceUserService(EcommerceDAOUserInterface userDAO) {this.userDAO = userDAO;}

    public EcommerceUser save(EcommerceUser user) {return userDAO.save(user);}

    public Optional<EcommerceUser> getUser(String email) {return userDAO.findByEmail(email);}

    public Optional<EcommerceUser> findById(int userId) {
        Optional<EcommerceUser> optional = userDAO.findById(userId);
        if(optional.isPresent()) {
            EcommerceUser user = optional.get();
            return Optional.of(user);
        }
        return optional;
    }
}
