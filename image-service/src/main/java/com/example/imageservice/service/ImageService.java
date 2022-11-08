package com.example.imageservice.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadFile(MultipartFile file);
    ByteArrayResource downloadFile(String fileName);

    String deleteFile(String fileName);

}
