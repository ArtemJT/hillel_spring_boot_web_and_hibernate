package com.example.hw_28_spring_boot_web.controller;

import com.example.hw_28_spring_boot_web.entity.Order;
import com.example.hw_28_spring_boot_web.entity.Product;
import com.example.hw_28_spring_boot_web.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        logInvokedMethod();
        return orderService.findAllOrders();
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        logInvokedMethod();
        return orderService.findAllProducts();
    }

    @PostMapping("/products/{quantity}")
    public void saveProducts(@PathVariable int quantity){
        logInvokedMethod();
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= quantity; i++) {
            Product product = new Product();
            product.setName("Product-" + i);
            product.setCost(Double.parseDouble(Integer.toString(i) + i + '.' + i + i));
            products.add(product);
        }
        orderService.addProduct(products);
    }

    @PostMapping
    public void saveOrder(@RequestParam int... id) {
        logInvokedMethod();
        orderService.addOrder(id);
    }

    private void logInvokedMethod() {
        log.info("{{}} WAS CALLED", Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
