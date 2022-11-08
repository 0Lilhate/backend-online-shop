package com.example.transactionservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentObject {
    private String orderNumber;
    private String emailUser;
    private String address;
    private Collection<ClothOrder> clothOrders = new ArrayList<>();
    private Long amountPayment;
}
