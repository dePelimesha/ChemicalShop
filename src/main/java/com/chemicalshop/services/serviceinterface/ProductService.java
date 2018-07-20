package com.chemicalshop.services.serviceinterface;

import com.chemicalshop.entities.ProductsEntity;

import java.util.List;

public interface ProductService {

    ProductsEntity getProductById(int id);
    void addProduct(ProductsEntity product);
    List<ProductsEntity> getAllProduct();
    List<ProductsEntity> getAllProduct(int pageNumber, int productsOnPage);
    List<ProductsEntity> getAllProductsByCategory(int categoryId);
    List<ProductsEntity> getProductsByCategory(int categoryId, int pageNumber, int productsOnPage);
    List<ProductsEntity> getProductsBySearch(String searchString, int pageNumber, int productsOnPage);
    List<ProductsEntity> getAllProductsBySearch(String searchString);
    void updateProduct(ProductsEntity product);
    ProductsEntity getByName(String productName);
}
