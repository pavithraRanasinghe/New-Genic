package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "return_request")
public class ReturnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "return_request_id")
    private long returnRequestId;
    @Column
    private String reason;
    @Column(name = "request_date")
    private Date requestDate;
    @Column(name = "request_time")
    private Date requestTime;
    @Column
    private String img;
    @OneToOne(mappedBy = "returnEntity")
    private OrderDetailEntity orderDetailEntity;

    public ReturnEntity() {
    }

    public ReturnEntity(long returnRequestId, String reason, Date requestDate, Date requestTime, String img, OrderDetailEntity orderDetailEntity) {
        this.returnRequestId = returnRequestId;
        this.reason = reason;
        this.requestDate = requestDate;
        this.requestTime = requestTime;
        this.img = img;
        this.orderDetailEntity = orderDetailEntity;
    }

    public long getReturnRequestId() {
        return returnRequestId;
    }

    public void setReturnRequestId(long returnRequestId) {
        this.returnRequestId = returnRequestId;
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

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    @Override
    public String toString() {
        return "ReturnEntity{" +
                "returnRequestId=" + returnRequestId +
                ", reason='" + reason + '\'' +
                ", requestDate=" + requestDate +
                ", requestTime=" + requestTime +
                ", img='" + img + '\'' +
                ", orderDetailEntity=" + orderDetailEntity +
                '}';
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public OrderDetailEntity getOrderDetailEntity() {
        return orderDetailEntity;
    }

    public void setOrderDetailEntity(OrderDetailEntity orderDetailEntity) {
        this.orderDetailEntity = orderDetailEntity;
    }

}
