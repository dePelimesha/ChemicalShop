package com.chemicalshop.dao.daoimpementation;

import com.chemicalshop.dao.AbstractDao;
import com.chemicalshop.dao.daointerface.ProductDAO;
import com.chemicalshop.entities.ProductsEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productDao")
public class ProductDAOImpl extends AbstractDao<Integer, ProductsEntity> implements ProductDAO {

    @Override
    public ProductsEntity getById(int id) {
        return getByKey(id);
    }

    @Override
    public void addProduct(ProductsEntity product) {
        save(product);
    }

    @Override
    public List<ProductsEntity> getAll() {
        Criteria criteria = createEntityCriteria();
        return (List<ProductsEntity>) criteria.list();
    }

    @Override
    public List<ProductsEntity> getAll(int pageNumber, int productsOnPage) {
        Criteria criteria = createEntityCriteria();
        criteria.setFirstResult(pageNumber * productsOnPage - productsOnPage);
        criteria.setMaxResults(productsOnPage);
        return (List<ProductsEntity>) criteria.list();
    }

    @Override
    public List<ProductsEntity> getAllByCriterion(Criterion criterion) {
        Criteria criteria = createEntityCriteria();
        return (List<ProductsEntity>) criteria.add(criterion).list();
    }

    @Override
    public List<ProductsEntity> getByCriterion(Criterion criterion, int pageNumber, int productsOnPage) {
        Criteria criteria = createEntityCriteria();
        criteria.setFirstResult(pageNumber * productsOnPage - productsOnPage);
        criteria.setMaxResults(productsOnPage);
        return (List<ProductsEntity>) criteria.add(criterion).list();
    }



    @Override
    public void updateProduct(ProductsEntity product) {
        update(product);
    }

    @Override
    public ProductsEntity getByName(String productName) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("productName", productName));
        return (ProductsEntity) criteria.uniqueResult();
    }
}
