package lk.robot.newgenic.dto.user.response;

import lk.robot.newgenic.dto.ProductDTO;
import lk.robot.newgenic.dto.user.CartProductDTO;

import java.util.List;

public class CartResponseDTO {

    private List<CartProductDTO> productList;
    private double totalWeight;
    private double totalProductPrice;

    public CartResponseDTO() {
    }

    public CartResponseDTO(List<CartProductDTO> productList,
                           double totalWeight,
                           double totalProductPrice) {
        this.productList = productList;
        this.totalWeight = totalWeight;
        this.totalProductPrice = totalProductPrice;
    }

    public List<CartProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<CartProductDTO> productList) {
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
