package com.chemicalshop.services.serviceinterface;

import com.chemicalshop.entities.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> getAll();
    CategoryEntity getById(int id);
    CategoryEntity getByCategoryName(String categoryName);
}
