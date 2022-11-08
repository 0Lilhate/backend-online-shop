package com.example.orderservice.repository;

import com.example.orderservice.domain.ClothOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClotheOrderRepository extends JpaRepository<ClothOrder, Long> {

    Optional<ClothOrder> findBySkuCode(String skuCode);
}
