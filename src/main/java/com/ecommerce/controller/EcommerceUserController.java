package com.ecommerce.controller;

import com.ecommerce.model.EcommerceUser;
import com.ecommerce.service.EcommerceUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class EcommerceUserController {
    private EcommerceUserService uService;

    @Autowired
    public EcommerceUserController(EcommerceUserService uService) {
        this.uService = uService;
    }

    @GetMapping
    public ResponseEntity<EcommerceUser> getUserInfo(HttpSession session) {
        EcommerceUser loggedInUser = (EcommerceUser) session.getAttribute("user");
        int userId = loggedInUser.getId();
        Optional<EcommerceUser> optional = uService.findById(userId);

        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optional.get());
    }
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody EcommerceUser createUser(@RequestBody EcommerceUser user){
        return uService.save(user);
    }
}
