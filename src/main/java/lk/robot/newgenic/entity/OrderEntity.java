package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;
    @Column(name = "order_uuid")
    private String orderUuid;
    private String status;
    @Column(name = "pick_up_date")
    private Date pickUpDate;
    @Column(name = "pick_up_time")
    private Time pickUpTime;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "order_time")
    private Time orderTime;
    @Column(name = "total_weight")
    private double totalWeight;
    @Column(name = "tracking_number")
    private String trackingNumber;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id")
    private UserEntity userEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_delivery_id")
    private DeliveryEntity deliveryEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id",referencedColumnName = "payment_id")
    private PaymentEntity paymentEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_shipping_detail_id")
    private UserAddressEntity shippingDetails;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_billing_detail_id")
    private UserAddressEntity billingDetail;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_discount_method_id")
    private DiscountMethodEntity discountMethodEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "return_request_id",referencedColumnName = "return_request_id")
    private ReturnEntity returnEntity;

    public OrderEntity() {
    }

    public OrderEntity(long orderId,
                       String orderUuid,
                       String status,
                       Date pickUpDate,
                       Time pickUpTime,
                       Date orderDate,
                       Time orderTime,
                       double totalWeight,
                       String trackingNumber,
                       UserEntity userEntity,
                       DeliveryEntity deliveryEntity,
                       PaymentEntity paymentEntity,
                       UserAddressEntity shippingDetails,
                       UserAddressEntity billingDetail,
                       DiscountMethodEntity discountMethodEntity,
                       ReturnEntity returnEntity) {
        this.orderId = orderId;
        this.status = status;
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.totalWeight = totalWeight;
        this.trackingNumber = trackingNumber;
        this.userEntity = userEntity;
        this.deliveryEntity = deliveryEntity;
        this.paymentEntity = paymentEntity;
        this.billingDetail = billingDetail;
        this.shippingDetails = shippingDetails;
        this.discountMethodEntity = discountMethodEntity;
        this.returnEntity = returnEntity;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(String orderUuid) {
        this.orderUuid = orderUuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Time getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(Time pickUpTime) {
        this.pickUpTime = pickUpTime;
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

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
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

    public UserAddressEntity getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(UserAddressEntity shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    public UserAddressEntity getBillingDetail() {
        return billingDetail;
    }

    public void setBillingDetail(UserAddressEntity billingDetail) {
        this.billingDetail = billingDetail;
    }

    public DiscountMethodEntity getDiscountMethodEntity() {
        return discountMethodEntity;
    }

    public void setDiscountMethodEntity(DiscountMethodEntity discountMethodEntity) {
        this.discountMethodEntity = discountMethodEntity;
    }

    public ReturnEntity getReturnEntity() {
        return returnEntity;
    }

    public void setReturnEntity(ReturnEntity returnEntity) {
        this.returnEntity = returnEntity;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderId=" + orderId +
                ", orderUuid='" + orderUuid + '\'' +
                ", status='" + status + '\'' +
                ", pickUpDate=" + pickUpDate +
                ", pickUpTime=" + pickUpTime +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", totalWeight=" + totalWeight +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", userEntity=" + userEntity +
                ", deliveryEntity=" + deliveryEntity +
                ", paymentEntity=" + paymentEntity +
                ", shippingDetails=" + shippingDetails +
                ", billingDetail=" + billingDetail +
                ", discountMethodEntity=" + discountMethodEntity +
                ", returnEntity=" + returnEntity +
                '}';
    }
}
