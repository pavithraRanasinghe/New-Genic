package lk.robot.newgenic.dto.user.response;

import lk.robot.newgenic.dto.ProductDTO;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class SaleResponseDTO {
    private long dealId;
    private String name;
    private String description;
    private Date startDate;
    private Time startTime;
    private Date endDate;
    private Time endTime;
    private double discountPercentage;
    private List<ProductDTO> productList;

    public SaleResponseDTO() {
    }

    public SaleResponseDTO(long dealId,
                           String name,
                           String description,
                           Date startDate,
                           Time startTime,
                           Date endDate,
                           Time endTime,
                           double discountPercentage,
                           List<ProductDTO> productList) {
        this.dealId = dealId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.discountPercentage = discountPercentage;
        this.productList =  productList;
    }

    public long getDealId() {
        return dealId;
    }

    public void setDealId(long dealId) {
        this.dealId = dealId;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDTO> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "SaleResponseDTO{" +
                "dealId=" + dealId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", startTime=" + startTime +
                ", endDate=" + endDate +
                ", endTime=" + endTime +
                ", discountPercentage=" + discountPercentage +
                ", productList=" + productList +
                '}';
    }
}

