package com.example.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_number")
    private String orderNumber;
    @Column(name = "email_user", unique = true)
    private String emailUser;

    @Column(name = "address")
    private String address;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<ClothOrder> clothOrders = new ArrayList<>();


}
