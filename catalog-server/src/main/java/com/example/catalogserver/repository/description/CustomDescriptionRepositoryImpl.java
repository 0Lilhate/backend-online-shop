package com.example.catalogserver.repository.description;

import com.example.catalogserver.domain.Description;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class CustomDescriptionRepositoryImpl implements CustomDescriptionRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Description> getByDescriptionOrCreate(Description description) {
        TypedQuery<Description> query = entityManager.
                createQuery("select d from Description d where d.aboutCloth =:about and d.textile =: textile",
                        Description.class);

        query.setParameter("about", description.getAboutCloth());
        query.setParameter("textile", description.getTextile());

        List<Description> descriptions = query.getResultList();
        if(descriptions.isEmpty()){
            return Optional.of(entityManager.merge(description));
        }
        else {
            return Optional.of(descriptions.get(0));
        }
    }
}
