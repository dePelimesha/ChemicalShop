package com.chemicalshop.services.serviceinterface;

import com.chemicalshop.entities.OrderinfoEntity;

import java.util.List;

public interface OrderInfoService {
    int addOrder(OrderinfoEntity order);
    List<OrderinfoEntity> getAllOrders();
    OrderinfoEntity getById(int id);
}
