package lk.robot.newgenic.dto.admin.request;

public class ProductRequestDTO {

    private String productCode;
    private String name;
    private String description;
    private long subCategoryId;
    private String brand;
    private String color;
    private String gender;
    private String size;
    private String image;
    private double weight;
    private int stock;
    private double buyingPrice;
    private double salePrice;
    private double retailPrice;
    private boolean active;
    private boolean isFreeShipping;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(String productCode,
                             String name,
                             String description,
                             long subCategoryId,
                             String brand,
                             String color,
                             String gender,
                             String size,
                             String image,
                             double weight,
                             int stock,
                             double buyingPrice,
                             double salePrice,
                             double retailPrice,
                             boolean active,
                             boolean isFreeShipping) {
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.subCategoryId = subCategoryId;
        this.brand = brand;
        this.color = color;
        this.gender = gender;
        this.size = size;
        this.image = image;
        this.weight = weight;
        this.stock = stock;
        this.buyingPrice = buyingPrice;
        this.salePrice = salePrice;
        this.retailPrice = retailPrice;
        this.active = active;
        this.isFreeShipping = isFreeShipping;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isFreeShipping() {
        return isFreeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        isFreeShipping = freeShipping;
    }

    @Override
    public String toString() {
        return "ProductRequestDTO{" +
                "productCode='" + productCode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", subCategoryId=" + subCategoryId +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", gender='" + gender + '\'' +
                ", size='" + size + '\'' +
                ", image='" + image + '\'' +
                ", weight=" + weight +
                ", stock=" + stock +
                ", buyingPrice=" + buyingPrice +
                ", salePrice=" + salePrice +
                ", retailPrice=" + retailPrice +
                ", active=" + active +
                ", isFreeShipping=" + isFreeShipping +
                '}';
    }
}
