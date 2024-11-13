package com.shopping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Products {
    @Id
    int productID;
    String productName;
    String productDescription;
    double productPrice;
    boolean productInStock;
    int productQuantity;

    @Override
    public String toString() {
        return "Products{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", productInStock=" + productInStock +
                ", productQuantity=" + productQuantity +
                '}';
    }
}
