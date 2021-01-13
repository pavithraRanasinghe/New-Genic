package lk.robot.newgenic.dto;

import lk.robot.newgenic.entity.DeliveryCostEntity;

public class DeliveryDTO {
    private long deliveryId;
    private String name;
    private DeliveryCostEntity costEntity;

    public DeliveryDTO() {
    }

    public DeliveryDTO(long deliveryId, String name, DeliveryCostEntity costEntity) {
        this.deliveryId = deliveryId;
        this.name = name;
        this.costEntity = costEntity;
    }

    public long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeliveryCostEntity getCostEntity() {
        return costEntity;
    }

    public void setCostEntity(DeliveryCostEntity costEntity) {
        this.costEntity = costEntity;
    }

    @Override
    public String toString() {
        return "DeliveryDTO{" +
                "deliveryId=" + deliveryId +
                ", name='" + name + '\'' +
                ", costEntity=" + costEntity +
                '}';
    }
}
