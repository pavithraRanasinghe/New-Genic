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
    @Column(name = "request_date")
    private Date requestDate;
    @Column(name = "request_time")
    private Time requestTime;
    @Column(name = "response_date")
    private Date responseDate;
    @Column(name = "response_time")
    private Time responseTime;
    @Column
    private String action;
    @OneToOne(mappedBy = "returnEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    private OrderEntity orderEntity;

    public ReturnEntity() {
    }

    public ReturnEntity(long returnRequestId,
                        Date requestDate,
                        Time requestTime,
                        Date responseDate,
                        Time responseTime,
                        String action,
                        OrderEntity orderEntity) {
        this.returnRequestId = returnRequestId;
        this.requestDate = requestDate;
        this.requestTime = requestTime;
        this.responseDate = responseDate;
        this.responseTime = responseTime;
        this.action = action;
        this.orderEntity = orderEntity;
    }

    public long getReturnRequestId() {
        return returnRequestId;
    }

    public void setReturnRequestId(long returnRequestId) {
        this.returnRequestId = returnRequestId;
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

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public Time getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Time responseTime) {
        this.responseTime = responseTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }


    @Override
    public String toString() {
        return "ReturnEntity{" +
                "returnRequestId=" + returnRequestId +
                ", requestDate=" + requestDate +
                ", requestTime=" + requestTime +
                ", responseDate=" + responseDate +
                ", responseTime=" + responseTime +
                ", action='" + action + '\'' +
                ", orderEntity=" + orderEntity +
                '}';
    }
}
