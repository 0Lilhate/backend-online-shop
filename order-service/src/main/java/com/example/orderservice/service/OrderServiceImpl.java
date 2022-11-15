package com.example.orderservice.service;

import com.example.orderservice.domain.ClothOrder;
import com.example.orderservice.domain.Order;
import com.example.orderservice.exception.NotFoundException;
import com.example.orderservice.repository.ClotheOrderRepository;
import com.example.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ClotheOrderRepository clotheOrderRepository;

    @Transactional
    @Override
    public Order placeOrder(Order order) {
        Optional<Order> findOrder = orderRepository.findByOrderNumber(order.getOrderNumber());

        if(findOrder.isPresent()){
            findOrder.get().setClothOrders(order.getClothOrders());
            findOrder.get().setAddress(order.getAddress());
            findOrder.get().setEmailUser(order.getEmailUser());
            findOrder.get().setOrderNumber(order.getOrderNumber());
            return orderRepository.save(findOrder.get());
        }else {
            order.setOrderNumber(UUID.randomUUID().toString());
            return orderRepository.save(order);
        }

    }



    @Override
    @Transactional
    public Order addNewClothe(String email, ClothOrder clothOrder) {
        Order order = orderRepository.findByEmailUser(email).orElseThrow(()-> new NotFoundException(email));
        order.getClothOrders().add(clothOrder);
        log.info("order clothe {} is save", clothOrder.getSkuCode());
        return orderRepository.save(order);
    }

    //может быть не правильно, нужно доработать
    @Transactional
    @Override
    public Order removeClothe(String email, String skuCode) {

        Order order = orderRepository.findByEmailUser(email).orElseThrow(()-> new NotFoundException(email));

        ClothOrder findClotheOrder = clotheOrderRepository.findBySkuCode(skuCode)
                .orElseThrow(()-> new NotFoundException(email));

        if(findClotheOrder.getQuantity()>1){
            order.getClothOrders().forEach(o->{
                if (o.equals(findClotheOrder)){
                    o.setQuantity(o.getQuantity()-1);
                }
            });
            Order orderSave = orderRepository.save(order);
            log.info("order item {} is remove 1 item", findClotheOrder.getSkuCode());
            return orderSave;
        }
        else {
            order.setClothOrders(order.getClothOrders().stream()
                    .filter(o->(!(o.getSkuCode().equals(skuCode)))).collect(Collectors.toList()));
            clotheOrderRepository.delete(findClotheOrder);
            Order orderSave = orderRepository.save(order);
            log.info("order item {} is delete", findClotheOrder.getSkuCode());
            return orderSave;
        }
    }

    @Override
    @Transactional
    public Order addOneQuantityInClothe(String email, String skuCode) {
        Order order = orderRepository.findByEmailUser(email).orElseThrow(()-> new NotFoundException(email));
        order.getClothOrders().forEach(o->{
            if(o.getSkuCode().equals(skuCode)){
                o.setQuantity(o.getQuantity()+1);
                log.info("order clothe {} add 1 item", o.getSkuCode());
            }
        });
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }


}
