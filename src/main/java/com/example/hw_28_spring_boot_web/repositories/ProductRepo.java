package com.example.hw_28_spring_boot_web.repositories;

import com.example.hw_28_spring_boot_web.entity.Order;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends CrudRepository<Order, Integer> {
}
