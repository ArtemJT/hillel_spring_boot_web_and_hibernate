package com.example.hw_28_spring_boot_web.services;

import com.example.hw_28_spring_boot_web.dto.OrderDto;
import com.example.hw_28_spring_boot_web.dto.ProductDto;
import com.example.hw_28_spring_boot_web.entity.Order;
import com.example.hw_28_spring_boot_web.entity.Product;
import com.example.hw_28_spring_boot_web.repositories.OrderRepo;
import com.google.common.collect.Streams;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static com.example.hw_28_spring_boot_web.utilities.Logger.logResponse;
import static com.example.hw_28_spring_boot_web.utilities.Mapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepo orderRepo;
    private final ProductService productService;

    public String addOrder(int... productId) throws EntityNotFoundException {
        List<ProductDto> products = productService.findAllProductsById(productId);
        double cost = products.stream().mapToDouble(ProductDto::getCost).sum();

        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setProducts(allToEntity(products, Product.class));
        order.setCost(cost);

        Order saveOrder = orderRepo.save(order);
        OrderDto orderDto = toDto(saveOrder, OrderDto.class);

        return logResponse("ORDER {" + orderDto + "} ADDED SUCCESSFULLY");
    }

    public String addProductsToOrder(int orderId, int... productId) throws EntityNotFoundException {
        OrderDto orderDto = findOrderById(orderId);
        List<ProductDto> orderProducts = orderDto.getProductsDto();
        List<ProductDto> productsToAdd = productService.findAllProductsById(productId);
        List<ProductDto> products = Stream.concat(orderProducts.stream(), productsToAdd.stream()).toList();
        double cost = products.stream().mapToDouble(ProductDto::getCost).sum();

        deleteOrder(orderId);

        orderDto.setProductsDto(products);
        orderDto.setCost(cost);

        Order order = toEntity(orderDto, Order.class);
        Order savedOrder = orderRepo.save(order);

        String response = "ORDER WITH ID={" + orderId + "} UPDATED SUCCESSFULLY. NEW ORDER ID={" + savedOrder.getId() + '}';
        return logResponse(response);
    }

    public OrderDto findOrderById(int orderId) throws EntityNotFoundException {
        Order order = orderRepo.findById(orderId).orElseThrow(EntityNotFoundException::new);
        return toDto(order, OrderDto.class);
    }


    public String deleteOrder(int orderId) throws EntityNotFoundException {
        OrderDto orderDto = findOrderById(orderId);
        orderRepo.delete(toEntity(orderDto, Order.class));
        return logResponse("ORDER {" + orderDto + "} DELETED");
    }

    public List<OrderDto> findAllOrders() throws EntityNotFoundException {
        List<Order> orders = Streams.stream(orderRepo.findAll()).toList();
        if (orders.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return allToDto(orders, OrderDto.class);
    }
}
