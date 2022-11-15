package com.example.catalogserver.service;

import com.example.catalogserver.domain.Clothe;
import com.example.catalogserver.domain.Gender;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CatalogService {
    List<Clothe> findClotheBySize(String size);
    Clothe addClothe(Clothe clothe);
    Clothe updateClothe(Clothe updateClothe);

    List<Clothe> getAllClothe();

    void deleteClotheById(Long id);

    void deleteClothe(Clothe clothe);

    Clothe getByIdClothe(Long id);

    Clothe addToClotheImage(Long id, String fileName) throws IOException;

    String getImageNameByIdClothe(Long id, String fileName);

    String deleteImageNameByIdClothe(Long id, String fileName);

//    List<Clothe> getClotheByGender(String gender);
}
