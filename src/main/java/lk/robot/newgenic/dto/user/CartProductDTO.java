package lk.robot.newgenic.dto.user;

public class CartProductDTO {

    private long productId;
    private String name;
    private String productCode;
    private String description;
    private String color;
    private double weight;
    private double salePrice;
    private double retailPrice;
    private boolean isFreeShipping;
    private int qty;
    private double qtyPrice;

    public CartProductDTO() {
    }

    public CartProductDTO(long productId,
                          String name,
                          String productCode,
                          String description,
                          String color,
                          double weight,
                          double salePrice,
                          double retailPrice,
                          boolean isFreeShipping,
                          int qty,
                          double qtyPrice) {
        this.productId = productId;
        this.name = name;
        this.productCode = productCode;
        this.description = description;
        this.color = color;
        this.weight = weight;
        this.salePrice = salePrice;
        this.retailPrice = retailPrice;
        this.isFreeShipping = isFreeShipping;
        this.qty = qty;
        this.qtyPrice = qtyPrice;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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

    public boolean isFreeShipping() {
        return isFreeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        isFreeShipping = freeShipping;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getQtyPrice() {
        return qtyPrice;
    }

    public void setQtyPrice(double qtyPrice) {
        this.qtyPrice = qtyPrice;
    }

    @Override
    public String toString() {
        return "CartProductDTO{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", productCode='" + productCode + '\'' +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                ", salePrice=" + salePrice +
                ", retailPrice=" + retailPrice +
                ", isFreeShipping=" + isFreeShipping +
                ", qty=" + qty +
                ", qtyPrice=" + qtyPrice +
                '}';
    }
}
