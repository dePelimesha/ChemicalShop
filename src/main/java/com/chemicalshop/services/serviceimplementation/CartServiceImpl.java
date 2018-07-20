package com.chemicalshop.services.serviceimplementation;

import com.chemicalshop.dao.daointerface.CartDAO;
import com.chemicalshop.entities.OrderpositionsEntity;
import com.chemicalshop.services.serviceinterface.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cartService")
public class CartServiceImpl implements CartService{

    private CartDAO cartDAO;

    @Autowired
    public CartServiceImpl(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @Override
    public void addPosition(OrderpositionsEntity orderPosition) {
        cartDAO.addPosition(orderPosition);
    }

    @Override
    public void incrementCount(OrderpositionsEntity orderPosition) {
        cartDAO.incrementCount(orderPosition);
    }

    @Override
    public void decrementCount(OrderpositionsEntity orderPosition) {
        cartDAO.decrementCount(orderPosition);
    }

    @Override
    public void deletePosition(OrderpositionsEntity orderPosition) {
        cartDAO.deletePosition(orderPosition);
    }

    @Override
    public double getPrice() {
        return cartDAO.getPrice();
    }

    @Override
    public int getPositionsCount() {
        return cartDAO.getPositionsCount();
    }

    @Override
    public List<OrderpositionsEntity> getPositions() {
        return cartDAO.getPositions();
    }

    @Override
    public void deleteAllPositions() {
        cartDAO.deleteAllPositions();
    }

    @Override
    public List<Integer> getAllId() {
        return cartDAO.getAllId();
    }
}
