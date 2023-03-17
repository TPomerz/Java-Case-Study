package com.ecommerce.service;

import com.ecommerce.dao.EcommerceDAOCart;
import com.ecommerce.dao.EcommerceDAOProductInterface;
import com.ecommerce.model.EcommerceCart;
import com.ecommerce.model.EcommerceProduct;
import com.ecommerce.model.EcommerceUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EcommerceProductServiceTest {
    public EcommerceProductService sut;

    @Mock
    private EcommerceProduct mockProduct;

    @Mock
    private List<EcommerceProduct> mockProductList;

    @Mock
    EcommerceDAOProductInterface mockProductRepository;

    @Mock
    EcommerceDAOCart mockCartRepository;

    private final Integer id = 1;

    @Test
    void findAllTest() {
        sut = new EcommerceProductService(mockProductRepository, null);
        Mockito.when(mockProductRepository.findAll(Sort.by("id"))).thenReturn(mockProductList);
        Optional<List<EcommerceProduct>> products = Optional.of(sut.findAll());
        Assertions.assertEquals(Optional.of(mockProductList), products);
    }

    @Test
    void findByIdTest() {
        sut = new EcommerceProductService(mockProductRepository, null);
        Mockito.when(mockProductRepository.findById(id)).thenReturn(Optional.of(mockProduct));
        Optional<EcommerceProduct> product = sut.findById(id);
        Assertions.assertEquals(Optional.of(mockProduct), product);
    }

    @Test
    void getCartTest(@Mock EcommerceDAOCart mockCartRepository, @Mock EcommerceDAOProductInterface mockProductRepository){
        sut = new EcommerceProductService(mockProductRepository, mockCartRepository);
        List<EcommerceCart> cartList = new LinkedList<EcommerceCart>();
        EcommerceCart cart = new EcommerceCart(id, new EcommerceUser(id), new EcommerceProduct (id));
        cartList.add(cart);
        Mockito.when(mockCartRepository.getCart(id)).thenReturn(cartList);
        List<EcommerceCart> carts = sut.getCart(id);
        Assertions.assertEquals(cartList, carts);
    }

    @Test
    void addCartTest(@Mock EcommerceDAOCart mockCartRepository, @Mock EcommerceDAOProductInterface mockProductRepository){
        EcommerceCart cart = new EcommerceCart(id, new EcommerceUser(id), new EcommerceProduct (id));
        sut = new EcommerceProductService(mockProductRepository, mockCartRepository);
        Mockito.when(mockCartRepository.save(cart)).thenReturn(cart);
        Optional <EcommerceCart> c = Optional.of(sut.addCart(cart));
        Assertions.assertEquals(Optional.of(cart), c);
    }

    @Test
    void clearCartTest(@Mock EcommerceDAOCart mockCartRepository, @Mock EcommerceDAOProductInterface mockProductRepository) {
        sut = new EcommerceProductService(mockProductRepository, mockCartRepository);
        sut.clearCart(id);
        verify(mockCartRepository).clearCart(id);
    }

    @Test
    void inCartTest(@Mock EcommerceDAOCart mockCartRepository, @Mock EcommerceDAOProductInterface mockProductRepository) {
        sut = new EcommerceProductService(mockProductRepository, mockCartRepository);
        List<EcommerceCart> cartList = new LinkedList<EcommerceCart>();
        EcommerceCart cart = new EcommerceCart(id, new EcommerceUser(id), new EcommerceProduct (id));
        cartList.add(cart);
        Mockito.when(mockCartRepository.findAll()).thenReturn(cartList);
        boolean b = sut.inCart(id, id);
        Assertions.assertEquals(true, b);
    }
    @Test
    void findCartTest(@Mock EcommerceDAOCart mockCartRepository, @Mock EcommerceDAOProductInterface mockProductRepository){
        sut = new EcommerceProductService(mockProductRepository, mockCartRepository);
        List<EcommerceCart> cartList = new LinkedList<EcommerceCart>();
        EcommerceCart cart = new EcommerceCart(id, new EcommerceUser(id), new EcommerceProduct (id));
        cartList.add(cart);
        Mockito.when(mockCartRepository.findAll()).thenReturn(cartList);
        int cId = sut.findCart(id, id);
        Assertions.assertEquals(1, cId);
    }
}
