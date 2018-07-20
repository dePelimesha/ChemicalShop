package com.chemicalshop.dao.daoimpementation;

import com.chemicalshop.dao.AbstractDao;
import com.chemicalshop.dao.daointerface.UserDAO;
import com.chemicalshop.entities.UsersEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDAOImpl extends AbstractDao<Integer, UsersEntity> implements UserDAO {

    @Override
    public int addUser(UsersEntity user) {
        return save(user);
    }

    @Override
    public UsersEntity getById(int id) {
        return getByKey(id);
    }

    @Override
    public UsersEntity getByUserName(String userName) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("login", userName));
        return (UsersEntity) criteria.uniqueResult();
    }
}
