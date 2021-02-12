package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "combination")
public class CombinationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "combination_id")
    private long combinationId;
    private int stock;
    private double weight;
    @Column(name = "buying_price")
    private double buyingPrice;
    @Column(name = "sale_price")
    private double salePrice;
    @Column(name = "retailPrice")
    private double retailPrice;
    private boolean active;
    @Column(name = "stock_change_date")
    private Date stockChangeDate;
    @Column(name = "stock_change_time")
    private Time stockChangeTime;
    @Column(name = "retail_price_change_date")
    private Date retailPriceChangeDate;
    @Column(name = "retailPriceChangeTime")
    private Time retailPriceChangeTime;

    public CombinationEntity() {
    }

    public CombinationEntity(long combinationId,
                             int stock,
                             double weight,
                             double buyingPrice,
                             double salePrice,
                             double retailPrice,
                             boolean active,
                             Date stockChangeDate,
                             Time stockChangeTime,
                             Date retailPriceChangeDate,
                             Time retailPriceChangeTime) {
        this.combinationId = combinationId;
        this.stock = stock;
        this.weight = weight;
        this.buyingPrice = buyingPrice;
        this.salePrice = salePrice;
        this.retailPrice = retailPrice;
        this.active = active;
        this.stockChangeDate = stockChangeDate;
        this.stockChangeTime = stockChangeTime;
        this.retailPriceChangeDate = retailPriceChangeDate;
        this.retailPriceChangeTime = retailPriceChangeTime;
    }

    public long getCombinationId() {
        return combinationId;
    }

    public void setCombinationId(long combinationId) {
        this.combinationId = combinationId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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

    public Date getStockChangeDate() {
        return stockChangeDate;
    }

    public void setStockChangeDate(Date stockChangeDate) {
        this.stockChangeDate = stockChangeDate;
    }

    public Time getStockChangeTime() {
        return stockChangeTime;
    }

    public void setStockChangeTime(Time stockChangeTime) {
        this.stockChangeTime = stockChangeTime;
    }

    public Date getRetailPriceChangeDate() {
        return retailPriceChangeDate;
    }

    public void setRetailPriceChangeDate(Date retailPriceChangeDate) {
        this.retailPriceChangeDate = retailPriceChangeDate;
    }

    public Time getRetailPriceChangeTime() {
        return retailPriceChangeTime;
    }

    public void setRetailPriceChangeTime(Time retailPriceChangeTime) {
        this.retailPriceChangeTime = retailPriceChangeTime;
    }

    @Override
    public String toString() {
        return "CombinationEntity{" +
                "combinationId=" + combinationId +
                ", stock=" + stock +
                ", weight=" + weight +
                ", buyingPrice=" + buyingPrice +
                ", salePrice=" + salePrice +
                ", retailPrice=" + retailPrice +
                ", active=" + active +
                ", stockChangeDate=" + stockChangeDate +
                ", stockChangeTime=" + stockChangeTime +
                ", retailPriceChangeDate=" + retailPriceChangeDate +
                ", retailPriceChangeTime=" + retailPriceChangeTime +
                '}';
    }
}
