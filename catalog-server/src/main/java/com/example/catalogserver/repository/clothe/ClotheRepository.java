package com.example.catalogserver.repository.clothe;

import com.example.catalogserver.domain.Clothe;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "clothe", path = "clothe")

public interface ClotheRepository extends JpaRepository<Clothe, Long>, CustomClothesRepository {


//    @RestResource(path = "names", rel = "findByName")
    List<Clothe> findByNameCloth(String nameCloth);




}
