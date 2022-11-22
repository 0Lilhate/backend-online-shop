package com.example.imageservice.rest;

import com.example.imageservice.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/file/upload/tocloth/{id}")
    public String uploadFile(@RequestPart(value = "file")MultipartFile file, @PathVariable(value = "id") Long id) throws IOException {

        return imageService.uploadFile(file, id);
    }

    @GetMapping("/file/download/{id}/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable(value = "fileName") String fileName
            , @PathVariable(value = "id") Long id) throws IOException {
        return new ResponseEntity<>(imageService.downloadFile(fileName, id), HttpStatus.OK);
    }

    @DeleteMapping("/file/delete/{id}/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable(value = "fileName") String filName,
                                             @PathVariable(value = "id") Long id) throws IOException {

        return new ResponseEntity<>(imageService.deleteFile(id, filName), HttpStatus.OK);
    }

}
