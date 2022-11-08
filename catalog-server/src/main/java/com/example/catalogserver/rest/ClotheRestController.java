package com.example.catalogserver.rest;

import com.example.catalogserver.domain.Clothe;
import com.example.catalogserver.service.CatalogService;
import lombok.AllArgsConstructor;
import lombok.val;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
public class ClotheRestController {
    private final CatalogService catalogService;




    @PostMapping(value = "/clothe/add")
    public ResponseEntity<Clothe> addClothe(@RequestBody Clothe clothe){
        Clothe addClothe =  catalogService.addClothe(clothe);

        return new ResponseEntity<>(addClothe, HttpStatus.CREATED);
    }


    @GetMapping(value = "/clothe/test")
    public ResponseEntity<String> test(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        val oauth = securityContext.getAuthentication();

        return new ResponseEntity<>("ты админ", HttpStatus.CREATED);
    }

    @PutMapping(value = "/clothe/update")
    public ResponseEntity<Clothe> updateClothe(@RequestBody Clothe clothe){
        Clothe updateClothe =  catalogService.updateClothe(clothe);

        return new ResponseEntity<>(updateClothe, HttpStatus.OK);
    }

}
