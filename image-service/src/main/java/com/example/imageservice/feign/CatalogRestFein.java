package com.example.imageservice.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@FeignClient("catalog")
public interface CatalogRestFein {

    @PostMapping(value = "/clothe/addimage/{id}")
    ResponseEntity<String> uploadImageForClothe(@PathVariable(value = "id") Long id,
                                                       @RequestParam(value = "fileName") String fileName);


    @GetMapping(value = "/clothe/getimage/{id}")
    public String getImageForClothe(@PathVariable(value = "id") Long id,
                                                    @RequestParam(value = "fileName") String fileName);

    @PutMapping(value = "/clothe/delete/image/{id}")
    public String deleteImageForClothe(@PathVariable(value = "id") Long id,
                                               @RequestParam(value = "fileName") String fileName);



}
