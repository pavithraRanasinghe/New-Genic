package lk.robot.newgenic.entity;

import javax.persistence.*;

@Entity
@Table(name = "variation_value")
public class VariationDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variation_value_id")
    private long variationValueId;
    private String value;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_variation_id")
    private VariationEntity variationEntity;

    public VariationDetailEntity() {
    }

    public VariationDetailEntity(long variationValueId,
                                 String value,
                                 VariationEntity variationEntity) {
        this.variationValueId = variationValueId;
        this.value = value;
        this.variationEntity = variationEntity;
    }

    public long getVariationValueId() {
        return variationValueId;
    }

    public void setVariationValueId(long variationValueId) {
        this.variationValueId = variationValueId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public VariationEntity getVariationEntity() {
        return variationEntity;
    }

    public void setVariationEntity(VariationEntity variationEntity) {
        this.variationEntity = variationEntity;
    }

    @Override
    public String toString() {
        return "VariationValueEntity{" +
                "variationValueId=" + variationValueId +
                ", value='" + value + '\'' +
                ", variationEntity=" + variationEntity +
                '}';
    }
}
