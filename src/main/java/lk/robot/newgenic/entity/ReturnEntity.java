package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

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
    private Time requestTime;
    @Column
    private String action;
    @OneToOne(mappedBy = "returnEntity")
    private OrderDetailEntity orderDetailEntity;

    public ReturnEntity() {
    }

    public ReturnEntity(long returnRequestId,
                        String reason,
                        Date requestDate,
                        Time requestTime,
                        OrderDetailEntity orderDetailEntity,
                        String action) {
        this.returnRequestId = returnRequestId;
        this.reason = reason;
        this.requestDate = requestDate;
        this.requestTime = requestTime;
        this.orderDetailEntity = orderDetailEntity;
        this.action = action;
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

    public OrderDetailEntity getOrderDetailEntity() {
        return orderDetailEntity;
    }

    public void setOrderDetailEntity(OrderDetailEntity orderDetailEntity) {
        this.orderDetailEntity = orderDetailEntity;
    }

    @Override
    public String toString() {
        return "ReturnEntity{" +
                "returnRequestId=" + returnRequestId +
                ", reason='" + reason + '\'' +
                ", requestDate=" + requestDate +
                ", requestTime=" + requestTime +
                ", action='" + action + '\'' +
                ", orderDetailEntity=" + orderDetailEntity +
                '}';
    }
}
