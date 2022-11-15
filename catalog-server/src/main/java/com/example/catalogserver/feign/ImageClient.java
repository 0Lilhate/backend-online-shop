//package com.example.catalogserver.feign;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.multipart.MultipartFile;
//
//@FeignClient("image")
//public interface ImageClient {
//    @PostMapping("/file/upload")
//    String uploadFileForImage(@RequestPart(value = "file") MultipartFile file);
//}