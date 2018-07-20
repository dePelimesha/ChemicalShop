package com.chemicalshop.dao.daoimpementation;

import com.chemicalshop.dao.daointerface.CartDAO;
import com.chemicalshop.entities.Cart;
import com.chemicalshop.entities.OrderpositionsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("cartDao")
public class CartDAOImpl implements CartDAO {

    private Cart cart;

    @Autowired
    public CartDAOImpl(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void addPosition(OrderpositionsEntity orderPosition) {
        cart.addPosition(orderPosition);
    }

    @Override
    public void incrementCount(OrderpositionsEntity orderPosition) {
        cart.incrementCount(orderPosition);
    }

    @Override
    public void decrementCount(OrderpositionsEntity orderPosition) {
        cart.decrementCount(orderPosition);
    }

    @Override
    public void deletePosition(OrderpositionsEntity orderPosition) {
        cart.deletePosition(orderPosition);
    }

    @Override
    public double getPrice() {
        return cart.checkPrice();
    }

    @Override
    public int getPositionsCount() {
        return cart.getCount();
    }

    @Override
    public List<OrderpositionsEntity> getPositions() {
        return cart.getOrderPositionsList();
    }

    @Override
    public void deleteAllPositions() {
        cart.setOrderPositionsList(new ArrayList<>());
    }

    @Override
    public List<Integer> getAllId() {
        return cart.getAllIdOfPositions();
    }
}
