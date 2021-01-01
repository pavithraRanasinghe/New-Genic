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
    @JoinColumn(name = "payment_id",referencedColumnName = "order_id")
    private PaymentEntity paymentEntity;
    @ManyToOne
    @JoinColumn(name = "fk_user_address_id")
    private UserAddressEntity userAddressEntity;

}
