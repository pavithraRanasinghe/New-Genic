package lk.robot.newgenic.dto;

import lk.robot.newgenic.dto.user.response.DeliveryCostDTO;

public class DeliveryDTO {
    private long deliveryId;
    private String name;
    private DeliveryCostDTO deliveryCostDTO;

    public DeliveryDTO() {
    }

    public DeliveryDTO(long deliveryId,
                       String name,
                       DeliveryCostDTO deliveryCostDTO) {
        this.deliveryId = deliveryId;
        this.name = name;
        this.deliveryCostDTO = deliveryCostDTO;
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

    public DeliveryCostDTO getDeliveryCostDTO() {
        return deliveryCostDTO;
    }

    public void setDeliveryCostDTO(DeliveryCostDTO deliveryCostDTO) {
        this.deliveryCostDTO = deliveryCostDTO;
    }

    @Override
    public String toString() {
        return "DeliveryDTO{" +
                "deliveryId=" + deliveryId +
                ", name='" + name + '\'' +
                ", deliveryCostDTO=" + deliveryCostDTO +
                '}';
    }
}
