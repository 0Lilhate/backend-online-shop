package com.example.catalogserver.repository.image;

import com.example.catalogserver.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long>, CustomImageRepository {

}
