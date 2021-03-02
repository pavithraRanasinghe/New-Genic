package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "variation_detail")
public class VariationDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variation_detail_id")
    private long variationDetailId;
    private String value;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_variation_id")
    private VariationEntity variationEntity;
    @OneToMany(mappedBy = "variationDetailEntity",fetch = FetchType.LAZY)
    private List<VariationCombinationDetailEntity> variationCombinationList;

    public VariationDetailEntity() {
    }

    public VariationDetailEntity(long variationDetailId,
                                 String value,
                                 VariationEntity variationEntity) {
        this.variationDetailId = variationDetailId;
        this.value = value;
        this.variationEntity = variationEntity;
    }

    public long getVariationDetailId() {
        return variationDetailId;
    }

    public void setVariationDetailId(long variationDetailId) {
        this.variationDetailId = variationDetailId;
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

    public List<VariationCombinationDetailEntity> getVariationCombinationList() {
        return variationCombinationList;
    }

    public void setVariationCombinationList(List<VariationCombinationDetailEntity> variationCombinationList) {
        this.variationCombinationList = variationCombinationList;
    }

    @Override
    public String toString() {
        return "VariationDetailEntity{" +
                "variationDetailId=" + variationDetailId +
                ", value='" + value + '\'' +
                ", variationEntity=" + variationEntity +
                '}';
    }
}
