package com.example.imageservice.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

public class ImageServiceImpl implements ImageService{
    @Override
    public String uploadFile(MultipartFile file) {
        return null;
    }

    @Override
    public ByteArrayResource downloadFile(String fileName) {
        return null;
    }

    @Override
    public String deleteFile(String fileName) {
        return null;
    }
}
