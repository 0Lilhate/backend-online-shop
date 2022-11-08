package com.example.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Order {



    private String orderNumber;

    private String emailUser;


    private String address;


    private Collection<ClothOrder> clothOrders = new ArrayList<>();


}
