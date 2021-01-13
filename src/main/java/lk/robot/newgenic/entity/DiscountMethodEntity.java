package lk.robot.newgenic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "discount_method")
public class DiscountMethodEntity {

    @Id
    @Column(name = "discount_method_id")
    private long discountMethodId;
    @Column(name = "price_limit")
    private double priceLimit;
    @Column
    private String type;
    @Column
    private double discount;
    @Column
    private boolean active;

    public DiscountMethodEntity() {
    }

    public DiscountMethodEntity(long discountMethodId,
                                double priceLimit,
                                String type,
                                double discount,
                                boolean active) {
        this.discountMethodId = discountMethodId;
        this.priceLimit = priceLimit;
        this.type = type;
        this.discount = discount;
        this.active = active;
    }

    public long getDiscountMethodId() {
        return discountMethodId;
    }

    public void setDiscountMethodId(long discountMethodId) {
        this.discountMethodId = discountMethodId;
    }

    public double getPriceLimit() {
        return priceLimit;
    }

    public void setPriceLimit(double priceLimit) {
        this.priceLimit = priceLimit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "DiscountMethodEntity{" +
                "discountMethodId=" + discountMethodId +
                ", priceLimit=" + priceLimit +
                ", type='" + type + '\'' +
                ", discount=" + discount +
                ", active=" + active +
                '}';
    }
}
