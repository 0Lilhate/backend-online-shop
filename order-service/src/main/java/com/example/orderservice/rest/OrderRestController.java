package com.example.orderservice.rest;

import com.example.orderservice.domain.ClothOrder;
import com.example.orderservice.domain.Order;
import com.example.orderservice.rest.dto.ClothOrderDto;
import com.example.orderservice.rest.dto.OrderDto;
import com.example.orderservice.service.ConvertClothOrder;
import com.example.orderservice.service.KafkaProducerService;
import com.example.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@AllArgsConstructor
public class OrderRestController {

    private final OrderService orderService;
    private final ConvertClothOrder convertClothOrder;

    @PostMapping(value = "/api/order")
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<Order> placeOrder(@RequestBody OrderDto orderDto){


        return CompletableFuture.supplyAsync(()-> orderService.placeOrder(convertClothOrder.dtoToOrder(orderDto)));
    }

    @GetMapping(value = "/api/order")
    @ResponseStatus(HttpStatus.OK)
    public Flux<OrderDto> getOrder(){
        return Flux.fromIterable(orderService.getAllOrder().stream()
                .map(convertClothOrder::orderToDto).toList());
    }


    @PutMapping(value = "/api/order/clothe/add_item/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<OrderDto> addOneQuantityInClotheUser(@PathVariable(name = "email") String email
            , @RequestParam(name = "sku_code") String skuCode){

        CompletableFuture<OrderDto> future = CompletableFuture
                .supplyAsync(()->convertClothOrder.orderToDto(orderService.addOneQuantityInClothe(email, skuCode)));

        Mono<OrderDto> monoFromFuture = Mono.fromFuture(future);
        return monoFromFuture;

    }


    @PutMapping(value = "/api/order/clothe/create/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<OrderDto> addNewClotheInOrder(@PathVariable(name = "email") String email
            , @RequestBody ClothOrderDto clothOrderDto){

        CompletableFuture<OrderDto> future = CompletableFuture
                .supplyAsync(()->convertClothOrder
                        .orderToDto(orderService.addNewClothe(email,
                                convertClothOrder.dtoClothToClothOrder(clothOrderDto))));

        Mono<OrderDto> monoFromFuture = Mono.fromFuture(future);
        return monoFromFuture;

    }

    @PutMapping(value = "/api/order/clothe/remove/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<OrderDto> removeClotheInUser(@PathVariable(name = "email") String email
            , @RequestParam(name = "sku_code") String skuCode){

        CompletableFuture<OrderDto> future = CompletableFuture
                .supplyAsync(()->convertClothOrder.orderToDto(orderService
                        .removeClothe(email, skuCode)));

        Mono<OrderDto> monoFromFuture = Mono.fromFuture(future);
        return monoFromFuture;

    }








}
