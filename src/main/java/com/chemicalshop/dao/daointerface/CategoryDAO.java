package com.chemicalshop.dao.daointerface;

import com.chemicalshop.entities.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CategoryDAO {
    List<CategoryEntity> getAllCategory();
    CategoryEntity getCategoryById(int id);
    CategoryEntity getByCategoryName(String categoryName);
}
