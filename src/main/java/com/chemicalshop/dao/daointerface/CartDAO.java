package com.chemicalshop.dao.daointerface;

import com.chemicalshop.entities.OrderpositionsEntity;

import java.util.List;

public interface CartDAO {
    void addPosition(OrderpositionsEntity orderPosition);
    void incrementCount(OrderpositionsEntity orderPosition);
    void decrementCount(OrderpositionsEntity orderPosition);
    void deletePosition(OrderpositionsEntity orderPosition);
    double getPrice();
    int getPositionsCount();
    List<OrderpositionsEntity> getPositions();
    void deleteAllPositions();
    List<Integer> getAllId();
}
