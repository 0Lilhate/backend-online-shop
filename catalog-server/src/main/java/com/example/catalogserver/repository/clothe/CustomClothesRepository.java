package com.example.catalogserver.repository.clothe;

import com.example.catalogserver.domain.Clothe;
import com.example.catalogserver.domain.Size;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface CustomClothesRepository {
    @RestResource(path = "gender", rel = "findByGenre")
    List<Clothe> findByGender(@Param("gender")String gender);

    List<Clothe> findClotheBySize(Size size);
}
