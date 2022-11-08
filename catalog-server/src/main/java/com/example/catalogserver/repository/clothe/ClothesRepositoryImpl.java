package com.example.catalogserver.repository.clothe;

import com.example.catalogserver.domain.Clothe;
import com.example.catalogserver.domain.Size;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClothesRepositoryImpl implements CustomClothesRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Clothe> findByGender(String gender) {

        TypedQuery<Clothe> query = entityManager
                .createQuery("select g from Clothe g where g.gender =: gender", Clothe.class);

        query.setParameter("gender", gender);
        List<Clothe> clothes = query.getResultList();

        return clothes;
    }

    @Override
    public List<Clothe> findClotheBySize(Size size) {

//        TypedQuery<Clothe> query = entityManager
//                .createQuery("select g from Clothe g where g.gender =: gender", Clothe.class);
//
//        query.setParameter("gender", gender);
//        List<Clothe> clothes = query.getResultList();

        return null;
    }
}
