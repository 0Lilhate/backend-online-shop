package com.example.apigatewayserver.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class GatewayRotes {


    private TokenRelayGatewayFilterFactory filterFactory;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("catalog", r -> r.path("/clothe/**")
                        .filters(f -> f.filter(filterFactory.apply())).uri("lb://catalog"))
                .route("order", r -> r.path("/api/order/**")
                        .filters(f -> f.filter(filterFactory.apply())).uri("lb://order"))
                .route("transaction", r -> r.path("/api/charge/**")
                        .filters(f -> f.filter(filterFactory.apply())).uri("lb://transaction"))
                .build();
    }
}
