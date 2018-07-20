package com.chemicalshop.dao.daoimpementation;

import com.chemicalshop.dao.AbstractDao;
import com.chemicalshop.dao.daointerface.UserStatusDAO;
import com.chemicalshop.entities.UserstatusEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userStatusDao")
public class UserStatusDAOImpl extends AbstractDao<Integer, UserstatusEntity> implements UserStatusDAO{

    @Override
    public List<UserstatusEntity> getAllStatus() {
        return createEntityCriteria().list();
    }

    @Override
    public UserstatusEntity getStatusById(int id) {
        return getByKey(id);
    }
}
