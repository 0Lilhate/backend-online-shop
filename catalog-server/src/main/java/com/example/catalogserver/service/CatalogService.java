package com.example.catalogserver.service;

import com.example.catalogserver.domain.Clothe;

import java.util.List;

public interface CatalogService {
    List<Clothe> findClotheBySize(String size);
    Clothe addClothe(Clothe clothe);
    Clothe updateClothe(Clothe updateClothe);
}
