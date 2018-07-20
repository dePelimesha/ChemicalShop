package com.chemicalshop.dao.daoimpementation;

import com.chemicalshop.dao.AbstractDao;
import com.chemicalshop.dao.daointerface.OrderInfoDAO;
import com.chemicalshop.entities.OrderinfoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderInfoDao")
public class OrderInfoDAOImpl extends AbstractDao<Integer, OrderinfoEntity> implements OrderInfoDAO {
    @Override
    public int addOrder(OrderinfoEntity order) {
        return save(order);
    }

    @Override
    public List<OrderinfoEntity> getAllOrders() {
        return createEntityCriteria().list();
    }

    @Override
    public OrderinfoEntity getById(int id) {
        return getByKey(id);
    }
}
