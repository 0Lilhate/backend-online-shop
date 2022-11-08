package com.example.catalogserver.repository.description;

import com.example.catalogserver.domain.Description;

import java.util.Optional;

public interface CustomDescriptionRepository {
    public Optional<Description> getByDescriptionOrCreate(Description description);
}
