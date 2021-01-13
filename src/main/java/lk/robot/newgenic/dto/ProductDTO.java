package lk.robot.newgenic.dto;


import lk.robot.newgenic.enums.Gender;

import java.util.Date;

public class ProductDTO {

    private long productId;
    private String productCode;
    private String name;
    private String description;
    private int stock;
    private String color;
    private String size;
    private String gender;
    private double buyingPrice;
    private double salePrice;
    private double retailPrice;
    private Date addedDate;
    private boolean active;
    private boolean isFreeShipping;

    public ProductDTO() {
    }

    public ProductDTO(
            long productId,
            String productCode,
            String name,
            String description,
            int stock,
            String color,
            String size,
            String gender,
            double buyingPrice,
            double salePrice,
            double retailPrice,
            Date addedDate,
            boolean active,
            boolean isFreeShipping) {
        this.productId = productId;
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.color = color;
        this.size = size;
        this.gender = gender;
        this.buyingPrice = buyingPrice;
        this.salePrice = salePrice;
        this.retailPrice = retailPrice;
        this.addedDate = addedDate;
        this.active = active;
        this.isFreeShipping = isFreeShipping;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId=" + productId +
                ", productCode='" + productCode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", gender='" + gender + '\'' +
                ", buyingPrice=" + buyingPrice +
                ", salePrice=" + salePrice +
                ", retailPrice=" + retailPrice +
                ", addedDate=" + addedDate +
                ", active=" + active +
                '}';
    }
}
