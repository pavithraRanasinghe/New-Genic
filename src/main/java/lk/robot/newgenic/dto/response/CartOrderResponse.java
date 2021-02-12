package lk.robot.newgenic.dto.response;

import lk.robot.newgenic.dto.DeliveryDTO;
import lk.robot.newgenic.dto.ProductDTO;

import java.util.List;

public class CartOrderResponse {
    private List<ProductDTO> productDTOList;
    private List<DeliveryDTO> deliveryDTOList;
    private boolean isCartFreeShipping;
    private double discount;
    private double cartPrice;
    private double totalWeight;

    public CartOrderResponse() {
    }

    public CartOrderResponse(List<ProductDTO> productDTOList,
                             List<DeliveryDTO> deliveryDTOList,
                             boolean isCartFreeShipping,
                             double discount,
                             double cartPrice,
                             double totalWeight) {
        this.productDTOList = productDTOList;
        this.deliveryDTOList = deliveryDTOList;
        this.isCartFreeShipping = isCartFreeShipping;
        this.discount = discount;
        this.cartPrice = cartPrice;
        this.totalWeight = totalWeight;
    }

    public List<ProductDTO> getProductDTOList() {
        return productDTOList;
    }

    public void setProductDTOList(List<ProductDTO> productDTOList) {
        this.productDTOList = productDTOList;
    }

    public List<DeliveryDTO> getDeliveryDTOList() {
        return deliveryDTOList;
    }

    public void setDeliveryDTOList(List<DeliveryDTO> deliveryDTOList) {
        this.deliveryDTOList = deliveryDTOList;
    }

    public boolean isCartFreeShipping() {
        return isCartFreeShipping;
    }

    public void setCartFreeShipping(boolean cartFreeShipping) {
        isCartFreeShipping = cartFreeShipping;
    }

    public double isDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
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
        return "CartOrderResponse{" +
                "productDTOList=" + productDTOList +
                ", deliveryDTOList=" + deliveryDTOList +
                ", isCartFreeShipping=" + isCartFreeShipping +
                ", discount=" + discount +
                ", cartPrice=" + cartPrice +
                ", totalWeight=" + totalWeight +
                '}';
    }
}
