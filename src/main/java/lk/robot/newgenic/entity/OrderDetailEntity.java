package lk.robot.newgenic.entity;

import javax.persistence.*;


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
    @ManyToOne
    @JoinColumn(name = "fk_combination_id")
    private CombinationEntity combinationEntity;
    @ManyToOne
    @JoinColumn(name = "fk_order_id")
    private OrderEntity orderEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_return_detail_id", referencedColumnName = "return_detail_id")
    private ReturnDetailEntity returnDetailEntity;


    public OrderDetailEntity() {
    }

    public OrderDetailEntity(long orderDetailId,
                             int quantity,
                             double orderPrice,
                             CombinationEntity combinationEntity,
                             OrderEntity orderEntity,
                             ReturnDetailEntity returnDetailEntity) {
        this.orderDetailId = orderDetailId;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
        this.combinationEntity = combinationEntity;
        this.orderEntity = orderEntity;
        this.returnDetailEntity = returnDetailEntity;
    }

    public long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public CombinationEntity getCombinationEntity() {
        return combinationEntity;
    }

    public void setCombinationEntity(CombinationEntity combinationEntity) {
        this.combinationEntity = combinationEntity;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public ReturnDetailEntity getReturnDetailEntity() {
        return returnDetailEntity;
    }

    public void setReturnDetailEntity(ReturnDetailEntity returnDetailEntity) {
        this.returnDetailEntity = returnDetailEntity;
    }

    @Override
    public String toString() {
        return "OrderDetailEntity{" +
                "orderDetailId=" + orderDetailId +
                ", quantity=" + quantity +
                ", orderPrice=" + orderPrice +
                ", combinationEntity=" + combinationEntity +
                ", orderEntity=" + orderEntity +
                ", returnDetailEntity=" + returnDetailEntity +
                '}';
    }
}
