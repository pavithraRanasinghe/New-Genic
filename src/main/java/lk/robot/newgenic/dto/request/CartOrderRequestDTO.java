package lk.robot.newgenic.dto.request;

public class CartOrderRequestDTO {
    private String cartId;
    private long deliveryId;
    private double deliveryCost;
    private double discount;
    private boolean isFreeShipping;
    private BillingDetail billingDetail;
    private ShippingDetail shippingDetail;
    private double cartPrice;
    private double totalWeight;

    public CartOrderRequestDTO() {
    }

    public CartOrderRequestDTO(String cartId,
                               long deliveryId,
                               double deliveryCost,
                               double discount,
                               boolean isFreeShipping,
                               BillingDetail billingDetail,
                               ShippingDetail shippingDetail,
                               double cartPrice,
                               double totalWeight) {
        this.cartId = cartId;
        this.deliveryId = deliveryId;
        this.deliveryCost = deliveryCost;
        this.discount = discount;
        this.isFreeShipping = isFreeShipping;
        this.billingDetail = billingDetail;
        this.shippingDetail = shippingDetail;
        this.cartPrice = cartPrice;
        this.totalWeight = totalWeight;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isFreeShipping() {
        return isFreeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        isFreeShipping = freeShipping;
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

    public double getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(double cartPrice) {
        this.cartPrice = cartPrice;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    @Override
    public String toString() {
        return "CartOrderRequestDTO{" +
                "cartId=" + cartId +
                ", deliveryId=" + deliveryId +
                ", deliveryCost=" + deliveryCost +
                ", discount=" + discount +
                ", isFreeShipping=" + isFreeShipping +
                ", billingDetail=" + billingDetail +
                ", shippingDetail=" + shippingDetail +
                ", cartPrice=" + cartPrice +
                ", totalWeight=" + totalWeight +
                '}';
    }
}
