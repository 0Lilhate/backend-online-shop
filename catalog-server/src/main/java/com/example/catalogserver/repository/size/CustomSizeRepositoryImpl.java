package com.example.catalogserver.repository.size;

import com.example.catalogserver.domain.Size;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class CustomSizeRepositoryImpl implements CustomSizeRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Size> getBySizeOrCreate(Size size) {
        TypedQuery<Size> query = entityManager.
                createQuery("select s from Size s where s.eurSize =:eur and s.ruSize =:ru and s.worldSize =:word",
                        Size.class);

        query.setParameter("eur", size.getEurSize());
        query.setParameter("ru", size.getRuSize());
        query.setParameter("word", size.getWorldSize());
        List<Size> sizes = query.getResultList();
        if(sizes.isEmpty()){
            return Optional.of(entityManager.merge(size));
        }
        else {
            return Optional.of(sizes.get(0));
        }
    }
}
