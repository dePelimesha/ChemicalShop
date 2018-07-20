package com.chemicalshop.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(
        value= WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS
)
public class Cart implements Serializable {
    private final static long serialVersionUID = 1L;

    private List<OrderpositionsEntity> orderPositionsList = new ArrayList<OrderpositionsEntity>();

    public List<OrderpositionsEntity> getOrderPositionsList() {
        return orderPositionsList;
    }

    public List<Integer> getAllIdOfPositions() {
        List<Integer> idList = new ArrayList<>();
        for (OrderpositionsEntity orderPosition: orderPositionsList) {
            idList.add(orderPosition.getProduct().getProductId());
        }
        return idList;
    }

    public void setOrderPositionsList(List<OrderpositionsEntity> orderPositionsList) {
        this.orderPositionsList = orderPositionsList;
    }

    public void addPosition(OrderpositionsEntity orderPosition) {
            orderPositionsList.add(orderPosition);
    }

    public void incrementCount(OrderpositionsEntity orderPosition) {
        orderPositionsList.get(orderPositionsList.indexOf(orderPosition)).incrementCount();
    }

    public void decrementCount(OrderpositionsEntity orderPosition) {
        orderPositionsList.get(orderPositionsList.indexOf(orderPosition)).decrementCount();
    }

    public void deletePosition(OrderpositionsEntity orderPosition) {
        orderPositionsList.remove(orderPosition);
    }

    public double checkPrice() {
        double price = 0;
        for (OrderpositionsEntity orderPosition: orderPositionsList) {
            price += orderPosition.getProduct().getPrice() * orderPosition.getProductCount();
        }
        return price;
    }

    public int getCount() {
        int count = 0;
        for (OrderpositionsEntity orderPosition: orderPositionsList) {
            count += orderPosition.getProductCount();
        }
        return count;
    }
}
