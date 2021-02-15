package lk.robot.newgenic.dto;

public class VariationDTO {

    private long variationId;
    private String variationName;
    private long variationDetailId;
    private String value;

    public VariationDTO() {
    }

    public VariationDTO(long variationId,
                        String variationName,
                        long variationDetailId,
                        String value) {
        this.variationId = variationId;
        this.variationName = variationName;
        this.variationDetailId = variationDetailId;
        this.value = value;
    }

    public long getVariationDetailId() {
        return variationDetailId;
    }

    public void setVariationDetailId(long variationDetailId) {
        this.variationDetailId = variationDetailId;
    }

    public String getVariationName() {
        return variationName;
    }

    public void setVariationName(String variationName) {
        this.variationName = variationName;
    }

    public long getVariationId() {
        return variationId;
    }

    public void setVariationId(long variationId) {
        this.variationId = variationId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "VariationDTO{" +
                "variationId=" + variationId +
                ", variationName='" + variationName + '\'' +
                ", variationDetailId=" + variationDetailId +
                ", value='" + value + '\'' +
                '}';
    }
}
