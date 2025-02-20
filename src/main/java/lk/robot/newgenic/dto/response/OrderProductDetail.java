package lk.robot.newgenic.dto.response;

import lk.robot.newgenic.dto.CombinationDTO;

public class OrderProductDetail {

    private String productId;
    private String productName;
    private String productCode;
    private int qty;
    private double orderPrice;
    private CombinationDTO combinationDTO;

    public OrderProductDetail() {
    }

    public OrderProductDetail(String productId,
                              String productName,
                              String productCode,
                              int qty,
                              double orderPrice,
                              CombinationDTO combinationDTO) {
        this.productId = productId;
        this.productName = productName;
        this.productCode = productCode;
        this.qty = qty;
        this.orderPrice = orderPrice;
        this.combinationDTO = combinationDTO;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public CombinationDTO getCombinationDTO() {
        return combinationDTO;
    }

    public void setCombinationDTO(CombinationDTO combinationDTO) {
        this.combinationDTO = combinationDTO;
    }

    @Override
    public String toString() {
        return "OrderProductDetail{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", qty=" + qty +
                ", orderPrice=" + orderPrice +
                ", combinationDTO=" + combinationDTO +
                '}';
    }
}
