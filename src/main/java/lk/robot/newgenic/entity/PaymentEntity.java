package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private long paymentId;
    @Column(name = "order_price")
    private double orderPrice;
    @Column(name = "delivery_price")
    private double deliverYPrice;
    @Column(name = "payment_date")
    private Date paymentDate;
    @Column(name = "payment_time")
    private Date paymentTime;
    @OneToOne(mappedBy = "paymentEntity")
    private OrderEntity orderEntity;

    public PaymentEntity() {
    }

    public PaymentEntity(long paymentId, double orderPrice, double deliverYPrice, Date paymentDate, Date paymentTime, OrderEntity orderEntity) {
        this.paymentId = paymentId;
        this.orderPrice = orderPrice;
        this.deliverYPrice = deliverYPrice;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.orderEntity = orderEntity;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public double getDeliverYPrice() {
        return deliverYPrice;
    }

    public void setDeliverYPrice(double deliverYPrice) {
        this.deliverYPrice = deliverYPrice;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    @Override
    public String toString() {
        return "PaymentEntity{" +
                "paymentId=" + paymentId +
                ", orderPrice=" + orderPrice +
                ", deliverYPrice=" + deliverYPrice +
                ", paymentDate=" + paymentDate +
                ", paymentTime=" + paymentTime +
                ", orderEntity=" + orderEntity +
                '}';
    }
}
