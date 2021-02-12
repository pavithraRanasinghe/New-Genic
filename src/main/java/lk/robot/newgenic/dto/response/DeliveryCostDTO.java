package lk.robot.newgenic.dto.response;

public class DeliveryCostDTO {
    private long deliveryCostId;
    private String district;
    private double cost;
    private double costPerExtra;

    public DeliveryCostDTO() {
    }

    public DeliveryCostDTO(long deliveryCostId,
                           String district,
                           double cost,
                           double costPerExtra) {
        this.deliveryCostId = deliveryCostId;
        this.district = district;
        this.cost = cost;
        this.costPerExtra = costPerExtra;
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

    @Override
    public String toString() {
        return "DeliveryCostDTO{" +
                "deliveryCostId=" + deliveryCostId +
                ", district='" + district + '\'' +
                ", cost=" + cost +
                ", costPerExtra=" + costPerExtra +
                '}';
    }
}
