package com.chemicalshop.services.serviceimplementation;

import com.chemicalshop.dao.daointerface.OrderPositionsDAO;
import com.chemicalshop.entities.OrderpositionsEntity;
import com.chemicalshop.services.serviceinterface.OrderPositionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("orderPositionsService")
@Transactional
public class OrderPositionsServiceImpl implements OrderPositionsService {

    @Autowired
    private OrderPositionsDAO orderPositionsDAO;

    @Override
    public void addOrderPosition(OrderpositionsEntity orderPosition) {
        orderPositionsDAO.addOrderPosition(orderPosition);
    }
}
