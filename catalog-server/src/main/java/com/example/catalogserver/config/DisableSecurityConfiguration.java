//package com.example.catalogserver.config;
//
//import com.example.catalogserver.converter.KeycloakRealmRoleConverter;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@ConditionalOnProperty(name = "keycloak.enabled", havingValue = "false")
//public class DisableSecurityConfiguration {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf()
//                .disable()
//                .authorizeRequests(authorizeRequests -> authorizeRequests
//                        .antMatchers(HttpMethod.GET, "/clothe/**").hasAnyRole("user")
//                        .antMatchers("/clothe/**").hasAnyRole("admin", "manager")
//                        .anyRequest().authenticated())
//                .oauth2ResourceServer(
//                        oauth2ResourceServer -> oauth2ResourceServer
//                                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
//                );
//        return http.build();
//    }
//
//    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
//        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
//        jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
//        return jwtConverter;
//    }
//
//}