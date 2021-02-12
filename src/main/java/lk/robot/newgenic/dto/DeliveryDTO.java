package lk.robot.newgenic.dto;

import lk.robot.newgenic.dto.response.DeliveryCostDTO;

import java.util.List;

public class DeliveryDTO {
    private long deliveryId;
    private String name;
    private List<DeliveryCostDTO> deliveryCostDTOList;

    public DeliveryDTO() {
    }

    public DeliveryDTO(long deliveryId,
                       String name,
                       List<DeliveryCostDTO> deliveryCostDTOList) {
        this.deliveryId = deliveryId;
        this.name = name;
        this.deliveryCostDTOList = deliveryCostDTOList;
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

    public List<DeliveryCostDTO> getDeliveryCostDTOList() {
        return deliveryCostDTOList;
    }

    public void setDeliveryCostDTOList(List<DeliveryCostDTO> deliveryCostDTOList) {
        this.deliveryCostDTOList = deliveryCostDTOList;
    }

    @Override
    public String toString() {
        return "DeliveryDTO{" +
                "deliveryId=" + deliveryId +
                ", name='" + name + '\'' +
                ", deliveryCostDTOList=" + deliveryCostDTOList +
                '}';
    }
}
