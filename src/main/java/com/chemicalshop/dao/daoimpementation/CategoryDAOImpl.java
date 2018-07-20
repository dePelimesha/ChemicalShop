package com.chemicalshop.dao.daoimpementation;

import com.chemicalshop.dao.AbstractDao;
import com.chemicalshop.dao.daointerface.CategoryDAO;
import com.chemicalshop.entities.CategoryEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoryDao")
public class CategoryDAOImpl extends AbstractDao<Integer, CategoryEntity> implements CategoryDAO {
    @Override
    public List<CategoryEntity> getAllCategory() {
        return (List<CategoryEntity>) createEntityCriteria().list();
    }

    @Override
    public CategoryEntity getCategoryById(int id) {
        return getByKey(id);
    }

    @Override
    public CategoryEntity getByCategoryName(String categoryName) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("categoryName", categoryName));
        return (CategoryEntity) criteria.uniqueResult();
    }
}
