package com.example.catalogserver.rest;

import com.example.catalogserver.domain.Clothe;
import com.example.catalogserver.domain.Gender;
import com.example.catalogserver.service.CatalogService;
import lombok.AllArgsConstructor;
import lombok.val;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class ClotheRestController {
    private final CatalogService catalogService;




    @PostMapping(value = "/clothe")
    public ResponseEntity<Clothe> addClothe(@RequestBody Clothe clothe){
        Clothe addClothe =  catalogService.addClothe(clothe);

        return new ResponseEntity<>(addClothe, HttpStatus.CREATED);
    }

    @GetMapping(value = "/clothe")
    public ResponseEntity<List<Clothe>> getAllClothe(){


        return new ResponseEntity<>(catalogService.getAllClothe(), HttpStatus.OK);
    }

    @GetMapping(value = "/clothe/{id}")
    public ResponseEntity<Clothe> getClotheById(@PathVariable(value = "id") Long id){


        return new ResponseEntity<>(catalogService.getByIdClothe(id), HttpStatus.OK);
    }



    @DeleteMapping(value = "/clothe/{id}")
    public ResponseEntity deleteClotheById(@PathVariable(value = "id") Long id){

        catalogService.deleteClotheById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/clothe")
    public ResponseEntity deleteClothe(@RequestBody Clothe clothe){

        catalogService.deleteClothe(clothe);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @GetMapping(value = "/clothe/test")
//    public ResponseEntity<String> test(){
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        val oauth = securityContext.getAuthentication();
//
//        return new ResponseEntity<>("ты админ", HttpStatus.CREATED);
//    }

    @PutMapping(value = "/clothe/update")
    public ResponseEntity<Clothe> updateClothe(@RequestBody Clothe clothe){
        Clothe updateClothe =  catalogService.updateClothe(clothe);

        return new ResponseEntity<>(updateClothe, HttpStatus.OK);
    }

    @PostMapping(value = "/clothe/addimage/{id}")
    public ResponseEntity<Clothe> uploadImageForClothe(@PathVariable(value = "id") Long id,
                                                       @RequestParam(value = "fileName") String fileName) throws IOException {
        try {
            return new ResponseEntity<>(catalogService.addToClotheImage(id, fileName),HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/clothe/getimage/{id}")
    public ResponseEntity<String> getImageForClothe(@PathVariable(value = "id") Long id,
                                                       @RequestParam(value = "fileName") String fileName) throws IOException {

        return new ResponseEntity<>(catalogService.getImageNameByIdClothe(id, fileName),HttpStatus.OK);

    }

    @PutMapping(value = "/clothe/delete/image/{id}")
    public ResponseEntity<String> deleteImageForClothe(@PathVariable(value = "id") Long id,
                                               @RequestParam(value = "fileName") String fileName){


        return new ResponseEntity<>(catalogService.deleteImageNameByIdClothe(id, fileName), HttpStatus.OK);
    }




}
