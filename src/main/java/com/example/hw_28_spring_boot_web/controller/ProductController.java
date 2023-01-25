package com.example.hw_28_spring_boot_web.controller;

import com.example.hw_28_spring_boot_web.dto.ProductDto;
import com.example.hw_28_spring_boot_web.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.example.hw_28_spring_boot_web.utilities.Logger.logInvokedMethod;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        logInvokedMethod();
        try {
            return productService.findAllProducts();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{quantity}")
    public ResponseEntity<String> saveProducts(@PathVariable int quantity) {
        logInvokedMethod();
        String response = productService.addProduct(quantity);
        return ResponseEntity.ok(response);
    }
}
