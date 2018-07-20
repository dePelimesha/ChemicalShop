package com.chemicalshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products", schema = "chemshop", catalog = "")
public class ProductsEntity {
    private int productId;
    private String productName;
    private Double price;
    private Integer inStock;
    private String physicalProperties;
    private Double molarMass;
    private String chemicalFormula;
    private byte[] image;
    private CategoryEntity category;
    private List<OrderpositionsEntity> orderPosition;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")
    @JsonView(Views.Public.class)
    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    public List<OrderpositionsEntity> getOrderPosition() {
        return orderPosition;
    }

    public void setOrderPosition(List<OrderpositionsEntity> orderPosition) {
        this.orderPosition = orderPosition;
    }

    @JsonView(Views.Public.class)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonView(Views.Public.class)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @JsonView(Views.Public.class)
    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    @JsonView(Views.Public.class)
    public String getPhysicalProperties() {
        return physicalProperties;
    }

    public void setPhysicalProperties(String physicalProperties) {
        this.physicalProperties = physicalProperties;
    }

    @JsonView(Views.Public.class)
    public Double getMolarMass() {
        return molarMass;
    }

    public void setMolarMass(Double molarMass) {
        this.molarMass = molarMass;
    }

    @JsonView(Views.Public.class)
    public String getChemicalFormula() {
        return chemicalFormula;
    }

    public void setChemicalFormula(String chemicalFormula) {
        this.chemicalFormula = chemicalFormula;
    }

    @JsonIgnore
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsEntity that = (ProductsEntity) o;

        if (productId != that.productId) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (inStock != null ? !inStock.equals(that.inStock) : that.inStock != null) return false;
        if (physicalProperties != null ? !physicalProperties.equals(that.physicalProperties) : that.physicalProperties != null)
            return false;
        if (molarMass != null ? !molarMass.equals(that.molarMass) : that.molarMass != null) return false;
        if (chemicalFormula != null ? !chemicalFormula.equals(that.chemicalFormula) : that.chemicalFormula != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (inStock != null ? inStock.hashCode() : 0);
        result = 31 * result + (physicalProperties != null ? physicalProperties.hashCode() : 0);
        result = 31 * result + (molarMass != null ? molarMass.hashCode() : 0);
        result = 31 * result + (chemicalFormula != null ? chemicalFormula.hashCode() : 0);
        return result;
    }
}

