package com.chemicalshop.dao.daointerface;

import com.chemicalshop.entities.ProductsEntity;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductDAO {
    ProductsEntity getById(int id);
    void addProduct(ProductsEntity product);
    List<ProductsEntity> getAll();
    List<ProductsEntity> getAll(int pageNumber, int productsOnPage);
    List<ProductsEntity> getAllByCriterion(Criterion criterion);
    List<ProductsEntity> getByCriterion(Criterion criterion, int pageNumber, int productsOnPage);
    void updateProduct(ProductsEntity product);
    ProductsEntity getByName(String productName);
}
