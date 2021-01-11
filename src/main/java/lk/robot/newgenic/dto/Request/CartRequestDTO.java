package lk.robot.newgenic.dto.Request;

public class CartRequestDTO {
    private long productId;
    private int qty;
    private double orderPrice;
    private double weight;

    public CartRequestDTO() {

    }

    public CartRequestDTO(long productId,
                          int qty,
                          double orderPrice,
                          double weight) {
        this.productId = productId;
        this.qty = qty;
        this.orderPrice = orderPrice;
        this.weight = weight;
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

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "CartRequestDTO{" +
                "productId=" + productId +
                ", qty=" + qty +
                ", orderPrice=" + orderPrice +
                ", weight=" + weight +
                '}';
    }
}
