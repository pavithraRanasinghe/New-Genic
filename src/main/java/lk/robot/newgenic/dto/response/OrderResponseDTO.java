package lk.robot.newgenic.dto.response;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class OrderResponseDTO {

    private long orderId;
    private Date orderDate;
    private Time orderTime;
    private String tracking;
    private String status;
    private double subPrice;
    private double discountPrice;
    private double shippingPrice;
    private List<OrderProductDetail> productDetailList;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(long orderId,
                            Date orderDate,
                            Time orderTime,
                            String tracking,
                            String status,
                            double subPrice,
                            double discountPrice,
                            double shippingPrice,
                            List<OrderProductDetail> productDetailList) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.tracking = tracking;
        this.status = status;
        this.subPrice = subPrice;
        this.discountPrice = discountPrice;
        this.shippingPrice = shippingPrice;
        this.productDetailList = productDetailList;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Time getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Time orderTime) {
        this.orderTime = orderTime;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getSubPrice() {
        return subPrice;
    }

    public void setSubPrice(double subPrice) {
        this.subPrice = subPrice;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public double getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(double shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public List<OrderProductDetail> getProductDetailList() {
        return productDetailList;
    }

    public void setProductDetailList(List<OrderProductDetail> productDetailList) {
        this.productDetailList = productDetailList;
    }

    @Override
    public String toString() {
        return "OrderResponseDTO{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", tracking='" + tracking + '\'' +
                ", status='" + status + '\'' +
                ", subPrice=" + subPrice +
                ", discountPrice=" + discountPrice +
                ", shippingPrice=" + shippingPrice +
                ", productDetailList=" + productDetailList +
                '}';
    }
}
