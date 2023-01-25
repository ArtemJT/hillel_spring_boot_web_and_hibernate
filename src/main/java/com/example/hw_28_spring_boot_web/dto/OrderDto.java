package com.example.hw_28_spring_boot_web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private int id;

    private LocalDate date;

    private double cost;

    private List<ProductDto> productsDto;

}
