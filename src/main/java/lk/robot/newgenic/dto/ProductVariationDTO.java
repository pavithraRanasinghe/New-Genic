package lk.robot.newgenic.dto;

import java.util.List;

public class ProductVariationDTO {

    private long variationId;
    private String variationName;
    private List<VariationDetailDTO> variationDetailList;

    public ProductVariationDTO() {
    }

    public ProductVariationDTO(long variationId,
                               String variationName,
                               List<VariationDetailDTO> variationDetailList) {
        this.variationId = variationId;
        this.variationName = variationName;
        this.variationDetailList = variationDetailList;
    }

    public long getVariationId() {
        return variationId;
    }

    public void setVariationId(long variationId) {
        this.variationId = variationId;
    }

    public String getVariationName() {
        return variationName;
    }

    public void setVariationName(String variationName) {
        this.variationName = variationName;
    }

    public List<VariationDetailDTO> getVariationDetailList() {
        return variationDetailList;
    }

    public void setVariationDetailList(List<VariationDetailDTO> variationDetailList) {
        this.variationDetailList = variationDetailList;
    }

    @Override
    public String toString() {
        return "ProductVariationDTO{" +
                "variationId=" + variationId +
                ", variationName='" + variationName + '\'' +
                ", variationDetailList=" + variationDetailList +
                '}';
    }
}
