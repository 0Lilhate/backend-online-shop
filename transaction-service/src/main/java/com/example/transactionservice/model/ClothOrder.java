package com.example.transactionservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClothOrder {


    private String skuCode;

    private Long price;

    private Integer quantity;


}
