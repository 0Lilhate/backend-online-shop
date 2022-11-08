package com.example.imageservice.rest;

import com.example.imageservice.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/file/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file")MultipartFile file){
        return new ResponseEntity<>(imageService.uploadFile(file), HttpStatus.CREATED);
    }

    @GetMapping("/file/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable(value = "fileName") String fileName){
        return new ResponseEntity<>(imageService.downloadFile(fileName), HttpStatus.OK);
    }

    @DeleteMapping("/file/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable(value = "fileName") String filName){
        return new ResponseEntity<>(imageService.deleteFile(filName), HttpStatus.OK);
    }

}
