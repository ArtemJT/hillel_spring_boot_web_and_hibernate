package com.example.hw_28_spring_boot_web.services;

import com.example.hw_28_spring_boot_web.entity.Order;
import com.example.hw_28_spring_boot_web.entity.Product;
import com.example.hw_28_spring_boot_web.repositories.OrderRepo;
import com.example.hw_28_spring_boot_web.repositories.ProductRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Streams;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private final ObjectMapper objectMapper;

    public void addProduct(@NonNull List<Product> products) {
        productRepo.saveAll(products);
        products.forEach(prod -> log.info("PRODUCT: {{}} ADDED SUCCESSFULLY", prod));
    }

    public List<Product> findAllProducts() {
        return Streams.stream(productRepo.findAll()).toList();
    }

    public Order addOrder(@NonNull int... productId) {
        List<Integer> idList = Arrays.stream(productId).boxed().toList();
        List<Product> products = Streams.stream(productRepo.findAllById(idList)).toList();
        double cost = products.stream().mapToDouble(Product::getCost).sum();
        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setProducts(products);
        order.setCost(cost);
        log.info("ORDER {{}} ADDED SUCCESSFULLY", order);
        return orderRepo.save(order);
    }

    public 

    public List<Order> findAllOrders() {
        return Streams.stream(orderRepo.findAll()).toList();
    }
}
