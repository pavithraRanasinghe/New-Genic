package lk.robot.newgenic.dto.Request;

public class OrderRequestDTO {
    private long productId;
    private int qty;
    private double salePrice;
    private double retailPrice;
    private BillingDetailDTO billingDetailDTO;
    private ShippingDetailDTO shippingDetailDTO;
    private long deliveryId;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(long productId,
                           int qty,
                           double salePrice,
                           double retailPrice,
                           BillingDetailDTO billingDetailDTO,
                           ShippingDetailDTO shippingDetailDTO,
                           long deliveryId) {
        this.productId = productId;
        this.qty = qty;
        this.salePrice = salePrice;
        this.retailPrice = retailPrice;
        this.billingDetailDTO = billingDetailDTO;
        this.shippingDetailDTO = shippingDetailDTO;
        this.deliveryId = deliveryId;
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

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BillingDetailDTO getBillingDetailDTO() {
        return billingDetailDTO;
    }

    public void setBillingDetailDTO(BillingDetailDTO billingDetailDTO) {
        this.billingDetailDTO = billingDetailDTO;
    }

    public ShippingDetailDTO getShippingDetailDTO() {
        return shippingDetailDTO;
    }

    public void setShippingDetailDTO(ShippingDetailDTO shippingDetailDTO) {
        this.shippingDetailDTO = shippingDetailDTO;
    }

    public long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(long deliveryId) {
        this.deliveryId = deliveryId;
    }

    @Override
    public String toString() {
        return "OrderRequestDTO{" +
                "productId=" + productId +
                ", qty=" + qty +
                ", salePrice=" + salePrice +
                ", retailPrice=" + retailPrice +
                ", billingDetailDTO=" + billingDetailDTO +
                ", shippingDetailDTO=" + shippingDetailDTO +
                ", deliveryId=" + deliveryId +
                '}';
    }
}
