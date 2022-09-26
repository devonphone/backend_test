package com.example.backendtest.product;

import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Table(appliesTo="product")
public class Product {

    @Id
    private String productCode;
    private String productName;
    private String subCategory;
    private String brand;
    private String retailPrice;
    private String status;

    public Product() {
    }

    public Product(String productCode, String productName, String subcategory, String brand, String retailPrice, String status) {
        this.productCode = productCode;
        this.productName = productName;
        this.subCategory = subcategory;
        this.brand = brand;
        this.retailPrice = retailPrice;
        this.status = status;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
