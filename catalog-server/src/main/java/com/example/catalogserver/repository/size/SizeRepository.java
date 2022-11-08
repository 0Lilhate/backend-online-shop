package com.example.catalogserver.repository.size;

import com.example.catalogserver.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size, Long>, CustomSizeRepository {


}
