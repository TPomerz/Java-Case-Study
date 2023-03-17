package com.ecommerce.controller;

import com.ecommerce.dto.EcommerceLoginRequest;
import com.ecommerce.dto.EcommerceRegisterRequest;
import com.ecommerce.model.EcommerceUser;
import com.ecommerce.service.EcommerceUserAuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

/**
 * Auth controller that handles requests sent to the "/auth" endpoint
 */
@RestController
@RequestMapping("/auth")
public class EcommerceUserAuthController {
    private final EcommerceUserAuthService aService;

    @Autowired
    public EcommerceUserAuthController(EcommerceUserAuthService aService){this.aService = aService;}

    /**
     * Handles Post requests sent to the "/auth/login" endpoint
     * @param loginRequest Contains the email and password of a user attempting to login
     * @param session Contains information about the current session
     * @return ResponseEntity with the User object if login was successful
     */
    @PostMapping("/login")
    public ResponseEntity<EcommerceUser> login(@RequestBody EcommerceLoginRequest loginRequest, HttpSession session) {
        Optional<EcommerceUser> optional = aService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());

        if(!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        session.setAttribute("user", optional.get());
        return ResponseEntity.ok(optional.get());
    }

    /**
     * Handles Post requests sent tot he "/auth/logout" endpoint
     * @param session Contains information about the current session
     * @return ResponseEntity to verify the request was successfully handled
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("user");

        return ResponseEntity.ok().build();
    }

    /**
     * Handles Post requests sent to the "/auth/register" endpoint and
     * creates a new user to be sent into the database
     * @param registerRequest Contains user-input information about a new user
     * @return TesponseEntity with the newly created user
     */
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
