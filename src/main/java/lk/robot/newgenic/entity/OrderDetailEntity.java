package lk.robot.newgenic.entity;

import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_detail")
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private long orderDetailId;
    @Column
    private int quantity;
    @Column(name = "order_price")
    private double orderPrice;
    @Column(name = "order_date")
    private Date orderDate;
    @ManyToOne
    @JoinColumn(name = "fk_product_id")
    private ProductEntity productEntity;
    @ManyToOne
    @JoinColumn(name = "fk_order_id")
    private OrderEntity orderEntity;
    @OneToOne
    @JoinColumn(name = "return_request_id",referencedColumnName = "return_request_id")
    private ReturnEntity returnEntity;

}
