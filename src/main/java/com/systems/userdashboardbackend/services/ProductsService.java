package com.systems.userdashboardbackend.services;


import com.systems.userdashboardbackend.models.Product;
import com.systems.userdashboardbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class ProductsService {

    @Autowired
    private ProductRepository productRepository;

    public ProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProduct(Long id) {
        Predicate<Product> productFound = product -> product.getId().equals(id);
        return productRepository.findAll()
                .stream()
                .filter(productFound)
                .findFirst();

    }


}
