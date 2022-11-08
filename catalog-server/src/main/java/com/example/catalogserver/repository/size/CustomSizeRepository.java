package com.example.catalogserver.repository.size;

import com.example.catalogserver.domain.Size;

import java.util.Optional;

public interface CustomSizeRepository {
    public Optional<Size> getBySizeOrCreate(Size size);
}
