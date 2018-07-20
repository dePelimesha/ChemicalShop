package com.chemicalshop.services.serviceimplementation;

import com.chemicalshop.dao.daointerface.CategoryDAO;
import com.chemicalshop.entities.CategoryEntity;
import com.chemicalshop.services.serviceinterface.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public List<CategoryEntity> getAll() {
        return categoryDAO.getAllCategory();
    }

    @Override
    public CategoryEntity getById(int id) {
        return categoryDAO.getCategoryById(id);
    }

    @Override
    public CategoryEntity getByCategoryName(String categoryName) {
        return categoryDAO.getByCategoryName(categoryName);
    }
}
