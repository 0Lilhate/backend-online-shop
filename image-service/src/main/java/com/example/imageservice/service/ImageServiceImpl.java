package com.example.imageservice.service;


import com.example.imageservice.feign.CatalogRestFein;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{

    @Value("${upload.path}")
    private String upload;

    private final CatalogRestFein catalogRestFein;


    @Override
    public String uploadFile(MultipartFile file, Long id){

        String pathUrl = new ClassPathResource(upload).getPath();

        try {
            Path pathNew =  Paths.get(pathUrl + File.separator + file.getOriginalFilename());

            Files.copy(file.getInputStream(), pathNew);
            catalogRestFein.uploadImageForClothe(id, file.getOriginalFilename());
            log.info("File {} download", file.getOriginalFilename());

        } catch (IOException e) {
            log.info("Error!!");
            throw new RuntimeException(e);
        }

        return file.getOriginalFilename();
    }

    @Override
    public byte[] downloadFile(String fileName, Long id) throws IOException {
        String file = catalogRestFein.getImageForClothe(id, fileName);
        if (file == null){
            return null;
        }
        String pathFile = new ClassPathResource(upload + File.separator + file).getPath();
        Path path = Paths.get(pathFile);
        ByteArrayResource byteArrayResource = new ByteArrayResource(Files.readAllBytes(path));
        return byteArrayResource.getByteArray();
    }

    @Override
    public String deleteFile(Long id,String fileName) {
        String fileDelete = catalogRestFein.deleteImageForClothe(id, fileName);
        if (fileDelete == null){
            return "file not found";
        }
        String pathFile = new ClassPathResource(upload + File.separator + fileDelete).getPath();
        File file = Paths.get(pathFile).toFile();
        if(file.delete()){
            return "success delete: " + fileName;
        }else {
            return "error delete file";
        }
    }
}
