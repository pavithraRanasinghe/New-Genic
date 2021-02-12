package lk.robot.newgenic.dto;

import java.util.List;

public class CombinationDTO {

    private long combinationId;
    private int stock;
    private double weight;
    private double salePrice;
    private double retailPrice;

    List<VariationDTO> variationList;

    public CombinationDTO() {

    }

    public CombinationDTO(long combinationId,
                          int stock,
                          double weight,
                          double salePrice,
                          double retailPrice,
                          List<VariationDTO> variationList) {
        this.combinationId = combinationId;
        this.stock = stock;
        this.weight = weight;
        this.salePrice = salePrice;
        this.retailPrice = retailPrice;
        this.variationList = variationList;
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

    public List<VariationDTO> getVariationList() {
        return variationList;
    }

    public void setVariationList(List<VariationDTO> variationList) {
        this.variationList = variationList;
    }

    @Override
    public String toString() {
        return "CombinationDTO{" +
                "combinationId=" + combinationId +
                ", stock=" + stock +
                ", weight=" + weight +
                ", salePrice=" + salePrice +
                ", retailPrice=" + retailPrice +
                ", variationList=" + variationList +
                '}';
    }
}
