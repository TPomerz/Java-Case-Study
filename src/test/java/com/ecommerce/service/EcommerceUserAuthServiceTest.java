package com.ecommerce.service;

import com.ecommerce.model.EcommerceUser;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EcommerceUserAuthServiceTest {

    public EcommerceUserAuthService sut;

    @Mock
    EcommerceUserService userServiceMock;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Starting AuthService tests...");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("AuthService Tests complete.");
    }

    @BeforeEach
    public void beforeEach(){
        sut = new EcommerceUserAuthService(userServiceMock);
    }

    @Test
    public void testAuthenticateUserWithCorrectCredentials(){
        String email = "testemail@gmail.com";
        String password = "password";
        EcommerceUser returnedUser = new EcommerceUser(1, email, "password", "first", "last", "country");

        Mockito.when(userServiceMock.getUser(email)).thenReturn(Optional.of(returnedUser));

        Optional<EcommerceUser> result = sut.authenticateUser(email, password);
        Assertions.assertEquals(Optional.of(returnedUser), result);
    }

    @Test
    public void testAuthenticateUserWithIncorrectPassword(){
        String email = "testemail@gmail.com";
        String password = "wrongPassword";
        EcommerceUser returnedUser = new EcommerceUser(1, email, "password", "first", "last", "country");

        Mockito.when(userServiceMock.getUser(email)).thenReturn(Optional.of(returnedUser));

        Optional<EcommerceUser> result = sut.authenticateUser(email, password);
        Assertions.assertEquals(Optional.empty(), result);

    }

    @Test
    public void testAuthenticateUserWithIncorrectEmail(){
        String email = "wrongemail@gmail.com";
        String password = "password";

        Mockito.when(userServiceMock.getUser(email)).thenReturn(Optional.empty());

        Optional<EcommerceUser> result = sut.authenticateUser(email, password);
        Assertions.assertEquals(Optional.empty(), result);
    }

    @Test
    public void testRegisterUser(){
        EcommerceUser testUser = new EcommerceUser("test@gmail.com", "pass", "test", "user", "country");
        EcommerceUser expected = new EcommerceUser(1, "test@gmail.com", "pass", "test", "user", "country");

        Mockito.when(userServiceMock.save(testUser)).thenReturn(expected);

        EcommerceUser result = sut.register(testUser);
        Assertions.assertEquals(expected, result);
    }
}
