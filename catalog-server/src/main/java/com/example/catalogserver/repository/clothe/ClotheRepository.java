package com.example.catalogserver.repository.clothe;

import com.example.catalogserver.domain.Clothe;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "clothe", path = "clothe")
public interface ClotheRepository extends PagingAndSortingRepository<Clothe, Long>, CustomClothesRepository {
    @Override
    List<Clothe> findAll();

    @RestResource(path = "names", rel = "findByName")
    List<Clothe> findByNameCloth(@Param("nameCloth")String nameCloth);


//    @RestResource(path = "gender", rel = "findByGender")
//    List<Clothe> findByGender(@Param("gender") String gender);

//    @RestResource(path = "price", rel = "findByPrice")
//    List<Clothe> findByPrice(@Param("price") String price);

}
