package lk.robot.newgenic.dto.user.request;

public class OrderRequestDTO {
    private long productId;
    private int qty;
    private double retailPrice;
    private long deliveryId;
    private double deliveryCost;
    private BillingDetail billingDetail;
    private ShippingDetail shippingDetail;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(long productId,
                           int qty,
                           double retailPrice,
                           long deliveryId,
                           double deliveryCost,
                           BillingDetail billingDetail,
                           ShippingDetail shippingDetail) {
        this.productId = productId;
        this.qty = qty;
        this.retailPrice = retailPrice;
        this.deliveryId = deliveryId;
        this.deliveryCost = deliveryCost;
        this.billingDetail = billingDetail;
        this.shippingDetail = shippingDetail;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
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
                "productId=" + productId +
                ", qty=" + qty +
                ", retailPrice=" + retailPrice +
                ", deliveryId=" + deliveryId +
                ", deliveryCost=" + deliveryCost +
                ", billingDetailDTO=" + billingDetail +
                ", shippingDetailDTO=" + shippingDetail +
                '}';
    }
}
