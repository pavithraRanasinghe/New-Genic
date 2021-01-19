package lk.robot.newgenic.dto.user.Request;

import lk.robot.newgenic.enums.Gender;

public class FilterDTO {

    private String sort;
    private String brand;
    private double maxPrice;
    private double minPrice;
    private int rate;
    private String color;
    private Gender gender;

    public FilterDTO() {
    }

    public FilterDTO(String sort, String brand, double maxPrice, double minPrice, int rate, String color, Gender gender) {
        this.sort = sort;
        this.brand = brand;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.rate = rate;
        this.color = color;
        this.gender = gender;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "FilterDTO{" +
                "sort='" + sort + '\'' +
                ", brand='" + brand + '\'' +
                ", maxPrice=" + maxPrice +
                ", minPrice=" + minPrice +
                ", rate=" + rate +
                ", color='" + color + '\'' +
                ", gender=" + gender +
                '}';
    }
}
