package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;
    @Column
    private String status;
    @Column(name = "order_price")
    private double orderPrice;
    @Column
    private double discount;
    @Column(name = "pick_up_date")
    private Date pickUpDate;
    @Column(name = "pick_up_time")
    private Date pickUpTime;
    @Column
    private double weight;
    @Column(name = "tracking_number")
    private String trackingNumber;
    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "fk_delivery_id")
    private DeliveryEntity deliveryEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id",referencedColumnName = "payment_id")
    private PaymentEntity paymentEntity;
    @ManyToOne
    @JoinColumn(name = "fk_user_address_id")
    private UserAddressEntity userAddressEntity;

    public OrderEntity() {
    }

    public OrderEntity(long orderId, String status, double orderPrice, double discount, Date pickUpDate, Date pickUpTime, double weight, String trackingNumber, UserEntity userEntity, DeliveryEntity deliveryEntity, PaymentEntity paymentEntity, UserAddressEntity userAddressEntity) {
        this.orderId = orderId;
        this.status = status;
        this.orderPrice = orderPrice;
        this.discount = discount;
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
        this.weight = weight;
        this.trackingNumber = trackingNumber;
        this.userEntity = userEntity;
        this.deliveryEntity = deliveryEntity;
        this.paymentEntity = paymentEntity;
        this.userAddressEntity = userAddressEntity;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Date getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(Date pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public DeliveryEntity getDeliveryEntity() {
        return deliveryEntity;
    }

    public void setDeliveryEntity(DeliveryEntity deliveryEntity) {
        this.deliveryEntity = deliveryEntity;
    }

    public PaymentEntity getPaymentEntity() {
        return paymentEntity;
    }

    public void setPaymentEntity(PaymentEntity paymentEntity) {
        this.paymentEntity = paymentEntity;
    }

    public UserAddressEntity getUserAddressEntity() {
        return userAddressEntity;
    }

    public void setUserAddressEntity(UserAddressEntity userAddressEntity) {
        this.userAddressEntity = userAddressEntity;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderId=" + orderId +
                ", status='" + status + '\'' +
                ", orderPrice=" + orderPrice +
                ", discount=" + discount +
                ", pickUpDate=" + pickUpDate +
                ", pickUpTime=" + pickUpTime +
                ", weight=" + weight +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", userEntity=" + userEntity +
                ", deliveryEntity=" + deliveryEntity +
                ", paymentEntity=" + paymentEntity +
                ", userAddressEntity=" + userAddressEntity +
                '}';
    }
}
