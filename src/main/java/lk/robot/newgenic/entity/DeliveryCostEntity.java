package lk.robot.newgenic.entity;

import javax.persistence.*;

@Entity
@Table(name = "delivery_cost")
public class DeliveryCostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_cost_id")
    private long deliveryCostId;
    private String district;
    private double cost;
    @Column(name = "cost_per_extra")
    private double costPerExtra;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_delivery_id")
    private DeliveryEntity deliveryEntity;

    public DeliveryCostEntity() {
    }

    public DeliveryCostEntity(long deliveryCostId,
                              String district,
                              double cost,
                              double costPerExtra,
                              DeliveryEntity deliveryEntity) {
        this.deliveryCostId = deliveryCostId;
        this.district = district;
        this.cost = cost;
        this.costPerExtra = costPerExtra;
        this.deliveryEntity = deliveryEntity;
    }


    public long getDeliveryCostId() {
        return deliveryCostId;
    }

    public void setDeliveryCostId(long deliveryCostId) {
        this.deliveryCostId = deliveryCostId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCostPerExtra() {
        return costPerExtra;
    }

    public void setCostPerExtra(double costPerExtra) {
        this.costPerExtra = costPerExtra;
    }

    public DeliveryEntity getDeliveryEntity() {
        return deliveryEntity;
    }

    public void setDeliveryEntity(DeliveryEntity deliveryEntity) {
        this.deliveryEntity = deliveryEntity;
    }

    @Override
    public String toString() {
        return "DeliveryCostEntity{" +
                "deliveryCostId=" + deliveryCostId +
                ", district='" + district + '\'' +
                ", cost=" + cost +
                ", costPerExtra=" + costPerExtra +
                ", deliveryEntity=" + deliveryEntity +
                '}';
    }
}
