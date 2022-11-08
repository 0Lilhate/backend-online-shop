package com.example.orderservice.rest.dto;

import com.example.orderservice.domain.ClothOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link ClothOrder} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClothOrderDto implements Serializable {
    private String name;
    private String color;
    private String size;
    private Long price;
    private Integer quantity;
}