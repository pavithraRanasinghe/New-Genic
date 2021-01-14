package lk.robot.newgenic.dto.response;

public class OrderProductDetail {

    private long productId;
    private String productName;
    private String productCode;
    private int qty;
    private double orderPrice;

    public OrderProductDetail() {
    }

    public OrderProductDetail(long productId,
                              String productName,
                              String productCode,
                              int qty,
                              double orderPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productCode = productCode;
        this.qty = qty;
        this.orderPrice = orderPrice;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
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

    @Override
    public String toString() {
        return "OrderProductDetail{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", qty=" + qty +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
