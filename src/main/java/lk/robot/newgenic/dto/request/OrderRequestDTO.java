package lk.robot.newgenic.dto.request;

public class OrderRequestDTO {
    private long combinationId;
    private int qty;
    private long deliveryId;
    private double deliveryCost;
    private BillingDetail billingDetail;
    private ShippingDetail shippingDetail;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(long combinationId,
                           int qty,
                           long deliveryId,
                           double deliveryCost,
                           BillingDetail billingDetail,
                           ShippingDetail shippingDetail) {
        this.combinationId = combinationId;
        this.qty = qty;
        this.deliveryId = deliveryId;
        this.deliveryCost = deliveryCost;
        this.billingDetail = billingDetail;
        this.shippingDetail = shippingDetail;
    }

    public long getCombinationId() {
        return combinationId;
    }

    public void setCombinationId(long combinationId) {
        this.combinationId = combinationId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BillingDetail getBillingDetail() {
        return billingDetail;
    }

    public void setBillingDetail(BillingDetail billingDetail) {
        this.billingDetail = billingDetail;
    }

    public ShippingDetail getShippingDetail() {
        return shippingDetail;
    }

    public void setShippingDetail(ShippingDetail shippingDetail) {
        this.shippingDetail = shippingDetail;
    }

    public long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    @Override
    public String toString() {
        return "OrderRequestDTO{" +
                "combinationId=" + combinationId +
                ", qty=" + qty +
                ", deliveryId=" + deliveryId +
                ", deliveryCost=" + deliveryCost +
                ", billingDetail=" + billingDetail +
                ", shippingDetail=" + shippingDetail +
                '}';
    }
}
