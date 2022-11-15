//package com.example.catalogserver.config;
//
//
//import com.example.catalogserver.converter.RealmRoleConverter;
//
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.jwt.Jwt;
//
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//
//
//@EnableWebSecurity
////@Configuration
////@EnableGlobalMethodSecurity(jsr250Enabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers( "/clothe/**").hasAnyRole( "admin", "manager")
//                .antMatchers(HttpMethod.GET, "/clothe/**").hasAnyRole("user")
//                .anyRequest().authenticated()
//                .and()
//                .cors()
//                .and()
//                .csrf()
//                .disable()
//                .oauth2ResourceServer()
//                .jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter()));
//    }
//
//
//
//
//    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
//        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
//        jwtConverter.setJwtGrantedAuthoritiesConverter(new RealmRoleConverter());
//        return jwtConverter;
//    }
//
//
//
//}
