package com.systems.userdashboardbackend.controllers;


import com.systems.userdashboardbackend.models.Product;
import com.systems.userdashboardbackend.repository.ProductRepository;
import com.systems.userdashboardbackend.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
     private ProductsService productsService;


    @GetMapping("/all")

    public List<Product> getAllProducts(){
        return productsService.getAllProducts();
    }
}