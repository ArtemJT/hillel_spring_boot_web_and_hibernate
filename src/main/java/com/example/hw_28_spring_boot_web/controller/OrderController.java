package com.example.hw_28_spring_boot_web.controller;

import com.example.hw_28_spring_boot_web.dto.OrderDto;
import com.example.hw_28_spring_boot_web.services.OrderService;
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
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAllOrders() {
        logInvokedMethod();
        try {
            return orderService.findAllOrders();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable int orderId) {
        logInvokedMethod();
        try {
            return orderService.findOrderById(orderId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> saveOrder(@RequestParam int... id) {
        logInvokedMethod();
        ResponseEntity<String> responseEntity;
        try {
            String response = orderService.addOrder(id);
            responseEntity = ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<String> updateOrder(@PathVariable int orderId, @RequestParam int... id) {
        logInvokedMethod();
        ResponseEntity<String> responseEntity;
        try {
            String response = orderService.addProductsToOrder(orderId, id);
            responseEntity = ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable int orderId) {
        logInvokedMethod();
        ResponseEntity<String> responseEntity;
        try {
            String response = orderService.deleteOrder(orderId);
            responseEntity = ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }
}
