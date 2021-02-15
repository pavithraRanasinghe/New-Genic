package lk.robot.newgenic.dto.response;

import lk.robot.newgenic.dto.CartProductDTO;

import java.util.List;

public class CartResponseDTO {

    private List<SingleProductResponseDTO> productList;
    private double totalWeight;
    private double totalProductPrice;

    public CartResponseDTO() {
    }

    public CartResponseDTO(List<SingleProductResponseDTO> productList,
                           double totalWeight,
                           double totalProductPrice) {
        this.productList = productList;
        this.totalWeight = totalWeight;
        this.totalProductPrice = totalProductPrice;
    }

    public List<SingleProductResponseDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<SingleProductResponseDTO> productList) {
        this.productList = productList;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(double totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    @Override
    public String toString() {
        return "CartResponseDTO{" +
                "productList=" + productList +
                ", totalWeight=" + totalWeight +
                ", totalProductPrice=" + totalProductPrice +
                '}';
    }
}
