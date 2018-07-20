package com.chemicalshop.dao.daoimpementation;

import com.chemicalshop.dao.AbstractDao;
import com.chemicalshop.dao.daointerface.OrderPositionsDAO;
import com.chemicalshop.entities.OrderpositionsEntity;
import org.springframework.stereotype.Repository;

@Repository("orderPositionDao")
public class OrderPositionsDAOImpl extends AbstractDao<Integer, OrderpositionsEntity> implements OrderPositionsDAO {
    @Override
    public void addOrderPosition(OrderpositionsEntity orderPosition) {
        save(orderPosition);
    }
}
