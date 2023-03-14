package com.ecommerce.controller;

import com.ecommerce.dto.EcommerceLoginRequest;
import com.ecommerce.dto.EcommerceRegisterRequest;
import com.ecommerce.model.EcommerceUser;
import com.ecommerce.service.EcommerceUserAuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class EcommerceUserAuthController {
    private final EcommerceUserAuthService aService;

    public EcommerceUserAuthController(EcommerceUserAuthService aService){this.aService = aService;}

    @PostMapping("/login")
    public ResponseEntity<EcommerceUser> login(@RequestBody EcommerceLoginRequest loginRequest, HttpSession session) {
        Optional<EcommerceUser> optional = aService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());

        if(!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        session.setAttribute("user", optional.get());
        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("user");

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<EcommerceUser> register(@RequestBody EcommerceRegisterRequest registerRequest) {
        EcommerceUser created = new EcommerceUser(0,
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getCountry());

        return ResponseEntity.status(HttpStatus.CREATED).body(aService.register(created));
    }
}
