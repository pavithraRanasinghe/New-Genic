package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

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
    private double deliveryPrice;
    @Column(name = "free_delivery_price")
    private double freeDeliveryPrice;
    @Column(name = "discount_price")
    private double discountPrice;
    @Column
    private double refund;
    @Column(name = "payment_date")
    private Date paymentDate;
    @Column(name = "payment_time")
    private Time paymentTime;
    @Column(name = "is_paid")
    private boolean isPaid;
    @OneToOne(mappedBy = "paymentEntity")
    private OrderEntity orderEntity;

    public PaymentEntity() {
    }

    public PaymentEntity(long paymentId,
                         double orderPrice,
                         double deliveryPrice,
                         double freeDeliveryPrice,
                         double discountPrice,
                         double refund,
                         Date paymentDate,
                         Time paymentTime,
                         boolean isPaid,
                         OrderEntity orderEntity) {
        this.paymentId = paymentId;
        this.orderPrice = orderPrice;
        this.deliveryPrice = deliveryPrice;
        this.freeDeliveryPrice = freeDeliveryPrice;
        this.discountPrice = discountPrice;
        this.refund = refund;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.isPaid = isPaid;
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

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Time getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Time paymentTime) {
        this.paymentTime = paymentTime;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public double getFreeDeliveryPrice() {
        return freeDeliveryPrice;
    }

    public void setFreeDeliveryPrice(double freeDeliveryPrice) {
        this.freeDeliveryPrice = freeDeliveryPrice;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public double getRefund() {
        return refund;
    }

    public void setRefund(double refund) {
        this.refund = refund;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "PaymentEntity{" +
                "paymentId=" + paymentId +
                ", orderPrice=" + orderPrice +
                ", deliveryPrice=" + deliveryPrice +
                ", freeDeliveryPrice=" + freeDeliveryPrice +
                ", discountPrice=" + discountPrice +
                ", refund=" + refund +
                ", paymentDate=" + paymentDate +
                ", paymentTime=" + paymentTime +
                ", isPaid=" + isPaid +
                ", orderEntity=" + orderEntity +
                '}';
    }
}
