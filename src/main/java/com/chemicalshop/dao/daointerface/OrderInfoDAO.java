package com.chemicalshop.dao.daointerface;

import com.chemicalshop.entities.OrderinfoEntity;

import java.util.List;

public interface OrderInfoDAO {
    int addOrder(OrderinfoEntity order);
    List<OrderinfoEntity> getAllOrders();
    OrderinfoEntity getById(int id);
}
