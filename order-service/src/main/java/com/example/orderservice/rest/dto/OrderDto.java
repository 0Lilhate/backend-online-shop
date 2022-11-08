package com.example.orderservice.rest.dto;

import com.example.orderservice.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A DTO for the {@link Order} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {
    private String emailUser;
    private String address;
    private Collection<ClothOrderDto> clothOrderDtoList = new ArrayList<>();
}