package com.chemicalshop.services.serviceimplementation;

import com.chemicalshop.dao.daointerface.ProductDAO;
import com.chemicalshop.entities.ProductsEntity;
import com.chemicalshop.services.serviceinterface.ProductService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO productDAO;

    @Override
    public ProductsEntity getProductById(int id) {
        return productDAO.getById(id);
    }

    @Override
    public void addProduct(ProductsEntity product) {
        productDAO.addProduct(product);
    }

    @Override
    public List<ProductsEntity> getAllProduct() {
        return productDAO.getAll();
    }

    @Override
    public List<ProductsEntity> getAllProduct(int pageNumber, int productsOnPage) {
        return productDAO.getAll(pageNumber, productsOnPage);
    }

    @Override
    public List<ProductsEntity> getAllProductsByCategory(int categoryId) {
        Criterion criterion = Restrictions.eq("category.categoryId", categoryId);
        return productDAO.getAllByCriterion(criterion);
    }

    @Override
    public List<ProductsEntity> getProductsByCategory(int categoryId, int pageNumber, int productsOnPage) {
        Criterion criterion = Restrictions.eq("category.categoryId", categoryId);
        return productDAO.getByCriterion(criterion, pageNumber, productsOnPage);
    }

    @Override
    public List<ProductsEntity> getProductsBySearch(String searchString, int pageNumber, int productsOnPage) {
        Criterion criterion = Restrictions.ilike("productName", searchString, MatchMode.ANYWHERE);
        return productDAO.getByCriterion(criterion, pageNumber, productsOnPage);
    }

    @Override
    public List<ProductsEntity> getAllProductsBySearch(String searchString) {
        Criterion criterion = Restrictions.ilike("productName", searchString, MatchMode.ANYWHERE);
        return productDAO.getAllByCriterion(criterion);
    }

    @Override
    public void updateProduct(ProductsEntity product) {
        productDAO.updateProduct(product);
    }

    @Override
    public ProductsEntity getByName(String productName) {
        return productDAO.getByName(productName);
    }
}
