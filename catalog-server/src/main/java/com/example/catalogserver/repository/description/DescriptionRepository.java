package com.example.catalogserver.repository.description;

import com.example.catalogserver.domain.Description;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepository extends JpaRepository<Description,Long>, CustomDescriptionRepository {
}
