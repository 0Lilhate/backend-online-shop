//package com.example.transactionservice.config;
//
//import com.example.transactionservice.converter.KeycloakRealmRoleConverter;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.web.reactive.config.EnableWebFlux;
//import reactor.core.publisher.Mono;
//
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//
//    public SecurityWebFilterChain configure(ServerHttpSecurity http) throws Exception {
//        http.authorizeExchange()
//                .pathMatchers("api/charge/**").hasAnyRole("admin","user","manager")
//                .anyExchange().authenticated()
//                .and()
//                .cors()
//                .and()
//                .csrf().disable()
//                .oauth2ResourceServer()
//                .jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter()));
//
//        return http.build();
//    }
//
//    private Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
//        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
//        jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
//        return (Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>>) Mono.just(jwtConverter);
//    }
//}
