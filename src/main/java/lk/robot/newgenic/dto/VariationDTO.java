package lk.robot.newgenic.dto;

public class VariationDTO {

    private long variationDetailId;
    private String variationName;
    private long variationId;
    private String value;

    public VariationDTO() {
    }

    public VariationDTO(long variationDetailId,
                        String variationName,
                        long variationId,
                        String value) {
        this.variationDetailId = variationDetailId;
        this.variationName = variationName;
        this.variationId = variationId;
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
                "variationDetailId=" + variationDetailId +
                ", variationId=" + variationId +
                ", value='" + value + '\'' +
                '}';
    }
}
