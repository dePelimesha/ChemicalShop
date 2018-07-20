package com.chemicalshop.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orderpositions", schema = "chemshop", catalog = "")
public class OrderpositionsEntity {
    private int positionId;
    private ProductsEntity product;
    private Integer productCount;
    private OrderinfoEntity order;

    public OrderpositionsEntity() {}

    public OrderpositionsEntity(ProductsEntity product) {
        super();
        this.product = product;
        this.productCount = 1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    public OrderinfoEntity getOrder() {
        return order;
    }

    public void setOrder(OrderinfoEntity order) {
        this.order = order;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    public ProductsEntity getProduct() {
        return product;
    }

    public void setProduct(ProductsEntity product) {
        this.product = product;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public void incrementCount() {
        this.productCount++;
    }

    public void decrementCount() {
        this.productCount--;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderpositionsEntity)) {
            return false;
        }
        OrderpositionsEntity orderPosition = (OrderpositionsEntity) o;
        return product.getProductName().equals(orderPosition.product.getProductName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}
