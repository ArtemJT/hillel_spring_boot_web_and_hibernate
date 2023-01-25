package com.example.hw_28_spring_boot_web.repositories;

import com.example.hw_28_spring_boot_web.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer> {
}
