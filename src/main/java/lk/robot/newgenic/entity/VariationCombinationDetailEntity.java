package lk.robot.newgenic.entity;

import javax.persistence.*;

@Entity
@Table(name = "variation_combination_detail")
public class VariationCombinationDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variation_combination_detail_id")
    private long variationCombinationId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_variation_detail_id")
    private VariationDetailEntity variationDetailEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_combination_id")
    private CombinationEntity combinationEntity;

    public VariationCombinationDetailEntity() {
    }

    public VariationCombinationDetailEntity(long variationCombinationId,
                                            VariationDetailEntity variationDetailEntity,
                                            CombinationEntity combinationEntity) {
        this.variationCombinationId = variationCombinationId;
        this.variationDetailEntity = variationDetailEntity;
        this.combinationEntity = combinationEntity;
    }

    public long getVariationCombinationId() {
        return variationCombinationId;
    }

    public void setVariationCombinationId(long variationCombinationId) {
        this.variationCombinationId = variationCombinationId;
    }

    public VariationDetailEntity getVariationDetailEntity() {
        return variationDetailEntity;
    }

    public void setVariationDetailEntity(VariationDetailEntity variationDetailEntity) {
        this.variationDetailEntity = variationDetailEntity;
    }

    public CombinationEntity getCombinationEntity() {
        return combinationEntity;
    }

    public void setCombinationEntity(CombinationEntity combinationEntity) {
        this.combinationEntity = combinationEntity;
    }

    @Override
    public String toString() {
        return "VariationCombinationDetail{" +
                "variationCombinationId=" + variationCombinationId +
                ", variationDetailEntity=" + variationDetailEntity +
                ", combinationEntity=" + combinationEntity +
                '}';
    }
}
