package com.example.learningspringbootproducts.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public Product findProductById(String id) {
        return productRepo.findById(id).orElseThrow();
    }

    public void removeProductById(String id) {
        productRepo.deleteById(id);
    }
}
