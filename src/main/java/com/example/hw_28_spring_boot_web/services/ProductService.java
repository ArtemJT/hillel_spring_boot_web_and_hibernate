package com.example.hw_28_spring_boot_web.services;

import com.example.hw_28_spring_boot_web.dto.ProductDto;
import com.example.hw_28_spring_boot_web.entity.Product;
import com.example.hw_28_spring_boot_web.repositories.ProductRepo;
import com.google.common.collect.Streams;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.hw_28_spring_boot_web.utilities.Logger.logResponse;
import static com.example.hw_28_spring_boot_web.utilities.Mapper.allToDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepo productRepo;

    public String addProduct(int quantity) {
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= quantity; i++) {
            Product product = new Product();
            product.setName("Product-" + i);
            product.setCost(Double.parseDouble(Integer.toString(i) + i + '.' + i + i));
            products.add(product);
        }

        productRepo.saveAll(products);
        return logResponse("{" + quantity + "} PRODUCT(S) ADDED SUCCESSFULLY");
    }

    public List<ProductDto> findAllProductsById(int... productId) throws EntityNotFoundException {
        List<Integer> idList = Arrays.stream(productId).boxed().toList();
        List<Product> products = Streams.stream(productRepo.findAllById(idList)).toList();
        if (products.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return allToDto(products, ProductDto.class);
    }

    public List<ProductDto> findAllProducts() throws EntityNotFoundException {
        List<Product> products = Streams.stream(productRepo.findAll()).toList();
        if (products.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return allToDto(products, ProductDto.class);
    }
}
