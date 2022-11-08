package com.example.catalogserver.repository.image;

import com.example.catalogserver.domain.Description;
import com.example.catalogserver.domain.Image;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class ImageRepositoryImpl implements CustomImageRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Image> getByImageOrCreate(Image image) {
        TypedQuery<Image> query = entityManager.
                createQuery("select i from Image i where i.urlImage=:url ",
                        Image.class);

        query.setParameter("url", image.getUrlImage());


        List<Image> images = query.getResultList();

        if(images.isEmpty()){
            return Optional.of(entityManager.merge(image));
        }
        else {
            return Optional.of(images.get(0));
        }
    }
}
