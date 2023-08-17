package com.systems.userdashboardbackend.services;


import com.systems.userdashboardbackend.models.Product;
import com.systems.userdashboardbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductRepository productRepository;

    public ProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product findProduct(Long id){
        Optional<Product> product = productRepository.findById(id);
        Product p = product.get();
        return p;
    }
}
