package com.example.imageservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String uploadFile(MultipartFile file, Long id) throws IOException;
    byte[] downloadFile(String fileName, Long id) throws IOException;

    String deleteFile(Long id,String fileName);

}
