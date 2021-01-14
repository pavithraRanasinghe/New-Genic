package lk.robot.newgenic.dto.response;

import java.sql.Date;
import java.sql.Time;

public class ReturnResponseDTO {
    
    private long returnId;
    private String reason;
    private Date requestDate;
    private Time requestTime;
    private String action;
    private long productId;
    private long orderId;
    private String productName;
    private int qty;
    private double orderPrice;

    public ReturnResponseDTO() {
    }

    public ReturnResponseDTO(long returnId,
                             String reason,
                             Date requestDate,
                             Time requestTime,
                             String action,
                             long productId,
                             long orderId,
                             String productName,
                             int qty,
                             double orderPrice) {
        this.returnId = returnId;
        this.reason = reason;
        this.requestDate = requestDate;
        this.requestTime = requestTime;
        this.action = action;
        this.productId = productId;
        this.orderId = orderId;
        this.productName = productName;
        this.qty = qty;
        this.orderPrice = orderPrice;
    }

    public long getReturnId() {
        return returnId;
    }

    public void setReturnId(long returnId) {
        this.returnId = returnId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Time getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Time requestTime) {
        this.requestTime = requestTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Override
    public String toString() {
        return "ReturnResponseDTO{" +
                "returnId=" + returnId +
                ", reason='" + reason + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", requestTime='" + requestTime + '\'' +
                ", action='" + action + '\'' +
                ", productId=" + productId +
                ", orderId=" + orderId +
                ", productName='" + productName + '\'' +
                ", qty=" + qty +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
