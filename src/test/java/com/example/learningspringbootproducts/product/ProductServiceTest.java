package com.example.learningspringbootproducts.product;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    ProductRepo productRepo = mock(ProductRepo.class);
    IdService idService = new IdService();
    ProductService productService = new ProductService(productRepo,idService);

    @Test
    void getAllProducts() {
        // GIVEN
        List<Product> expected = List.of(
                new Product("p1","iPhone",23.00),
                new Product("p2","test",19.78)
        );
        // WHEN
        when(productRepo.findAll()).thenReturn(expected);
        List<Product> actual = productService.getAllProducts();
        // THEN
        verify(productRepo).findAll();
        assertEquals(expected,actual);
    }

    @Test
    void findProductById() {
        // GIVEN
        Product expected = new Product("p1","test",10);

        // WHEN
        when(productRepo.findById("p1")).thenReturn(Optional.of(expected));
        Product actual = productService.findProductById("p1");
        // THEN
        assertEquals(expected,actual);
    }

    @Test
    void findProductById_invalidId_thenReturnException() {
        // WHEN
        when(productRepo.findById("p1")).thenThrow(new NoSuchElementException());
        // THEN
        assertThrows(NoSuchElementException.class, () -> productRepo.findById("p1"));
    }

    @Test
    void removeProductById() {
        doNothing().when(productRepo).deleteById(anyString());
        productService.removeProductById("p1");

        verify(productRepo, times(1)).deleteById("p1");
    }

    @Test
    void updateProductById() {
        // GIVEN
        //Product testProduct = new Product("p1","iPhone",21.00);
        Product expected = new Product("p1","iPhone",23.00);


        // WHEN
        //when(productRepo.save(any(Product.class))).thenReturn(testProduct);
        //productRepo.save(testProduct);
        when(productRepo.save(expected)).thenReturn(expected);
        Product actual = productService.updateProductById(expected.id(), expected.price());

        // THEN
        assertEquals(expected,actual);
    }
}