package com.chemicalshop.services.serviceimplementation;

import com.chemicalshop.dao.daointerface.OrderInfoDAO;
import com.chemicalshop.entities.OrderinfoEntity;
import com.chemicalshop.services.serviceinterface.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("orderService")
@Transactional
public class OrderInfoServiceImpl implements OrderInfoService{

    @Autowired
    private OrderInfoDAO orderInfoDAO;

    @Override
    public int addOrder(OrderinfoEntity order) {
        return orderInfoDAO.addOrder(order);
    }

    @Override
    public List<OrderinfoEntity> getAllOrders() {
        return orderInfoDAO.getAllOrders();
    }

    @Override
    public OrderinfoEntity getById(int id) {
        return orderInfoDAO.getById(id);
    }
}
