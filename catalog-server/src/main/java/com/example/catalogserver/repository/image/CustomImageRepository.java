package com.example.catalogserver.repository.image;

import com.example.catalogserver.domain.Image;

import java.util.Optional;

public interface CustomImageRepository {
    Optional<Image> getByImageOrCreate(Image image);
}
