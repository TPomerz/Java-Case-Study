package com.ecommerce.service;

import com.ecommerce.dao.EcommerceDAOUserInterface;
import com.ecommerce.exceptions.EcommerceEmailTakenException;
import com.ecommerce.model.EcommerceUser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EcommerceUserServiceTest {
    public EcommerceUserService sut;

    @Mock
    private EcommerceUser mockUser;

    @Mock
    private EcommerceUser mockUserByEmail = new EcommerceUser(55,"email", "password", "firstname", "lastname", "country");

    private String email = "mock@email.com";

    private String password = "password";

    @Mock
    EcommerceDAOUserInterface mockUserRepository;

    // set up the mocked user repository
    @BeforeAll
    public static void beforeAll() {
        System.out.println("Starting UserService tests...");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("UserService Tests complete.");
    }

    @Test
    public void testFindByEmailCorrectCredentialsProvided() {
        sut = new EcommerceUserService(mockUserRepository);
        Mockito.when(mockUserRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));
        Optional<EcommerceUser> user = sut.getUser(email);

        Assertions.assertEquals(Optional.of(mockUser), user);
    }

    @Test
    public void testFindByEmailIncorrectCredentialsProvided() {
        sut = new EcommerceUserService(mockUserRepository);
        Mockito.when(mockUserRepository.findByEmail(email)).thenReturn(Optional.empty());
        Optional<EcommerceUser> user = sut.getUser(email);

        Assertions.assertEquals(Optional.empty(), user);
    }


    @Test
    public void testSaveUserUserObjectArgument() {
        sut = new EcommerceUserService(mockUserRepository);
        Mockito.when(mockUserRepository.save(mockUser)).thenReturn(mockUser);
        EcommerceUser user = sut.save(mockUser);

        Assertions.assertEquals(mockUser, user);
    }

    @Test
    public void testFindByIntWhereUserIdInPersistence() {
        sut = new EcommerceUserService(mockUserRepository);
        Mockito.when(mockUserRepository.findById(1)).thenReturn(Optional.of(mockUser));
        Optional<EcommerceUser> user = sut.findById(1);

        Assertions.assertEquals(Optional.of(mockUser), user);
    }

    @Test
    public void testFindByIntWhereUserIdNotInPersistence() {
        sut = new EcommerceUserService(mockUserRepository);
        Optional<EcommerceUser> userNotFound = Optional.empty();
        Mockito.when(mockUserRepository.findById(1)).thenReturn(Optional.empty());
        Optional<EcommerceUser> user = sut.findById(1);

        Assertions.assertEquals(Optional.empty(), user);
    }
}
