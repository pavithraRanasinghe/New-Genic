package lk.robot.newgenic.dto.user.Request;

public class CartRequestDTO {
    private long productId;
    private int qty;
    private double price;
    private double weight;

    public CartRequestDTO() {

    }

    public CartRequestDTO(long productId,
                          int qty,
                          double price,
                          double weight) {
        this.productId = productId;
        this.qty = qty;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
}
