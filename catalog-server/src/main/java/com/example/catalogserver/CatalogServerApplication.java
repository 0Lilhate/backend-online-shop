package com.example.catalogserver;

import com.example.catalogserver.domain.*;
import com.example.catalogserver.service.CatalogService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.context.ApplicationContext;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableEurekaClient

public class CatalogServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogServerApplication.class, args);
//        ApplicationContext context = SpringApplication.run(CatalogServerApplication.class);
//        CatalogService catalogService = context.getBean(CatalogService.class);
//
//        Set<Size> sizeList = Set.of(new Size("44","39","L"));
//        List<Image> images = List.of(new Image("url5"), new Image("url6"));
//        Description description = new Description("aaa", "gggg");
//        Clothe clothe = new Clothe(3L,"update", "black",
//                4L, "666", sizeList, images, description, Gender.WOMAN);
//        System.out.println(catalogService.updateClothe(clothe));


    }

}
