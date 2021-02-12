package lk.robot.newgenic.entity;

import javax.persistence.*;

@Entity
@Table(name = "return_detail")
public class ReturnDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "return_detail_id")
    private long returnDetailId;
    private String reason;
    @Column(name = "return_qty")
    private int returnQty;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_return_id")
    private ReturnEntity returnEntity;
    @OneToOne(mappedBy = "returnDetailEntity",cascade = CascadeType.ALL, orphanRemoval = true)
    private OrderDetailEntity orderDetailEntity;

    public ReturnDetailEntity() {
    }

    public ReturnDetailEntity(long returnDetailId,
                              String reason,
                              int returnQty,
                              ReturnEntity returnEntity,
                              OrderDetailEntity orderDetailEntity) {
        this.returnDetailId = returnDetailId;
        this.reason = reason;
        this.returnQty = returnQty;
        this.returnEntity = returnEntity;
        this.orderDetailEntity = orderDetailEntity;
    }

    public long getReturnDetailId() {
        return returnDetailId;
    }

    public void setReturnDetailId(long returnDetailId) {
        this.returnDetailId = returnDetailId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(int returnQty) {
        this.returnQty = returnQty;
    }

    public ReturnEntity getReturnEntity() {
        return returnEntity;
    }

    public void setReturnEntity(ReturnEntity returnEntity) {
        this.returnEntity = returnEntity;
    }

    public OrderDetailEntity getOrderDetailEntity() {
        return orderDetailEntity;
    }

    public void setOrderDetailEntity(OrderDetailEntity orderDetailEntity) {
        this.orderDetailEntity = orderDetailEntity;
    }

    @Override
    public String toString() {
        return "ReturnDetailEntity{" +
                "returnDetailId=" + returnDetailId +
                ", reason='" + reason + '\'' +
                ", returnQty=" + returnQty +
                ", returnEntity=" + returnEntity +
                ", orderDetailEntity=" + orderDetailEntity +
                '}';
    }
}
