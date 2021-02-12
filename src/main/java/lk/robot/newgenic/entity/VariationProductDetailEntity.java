package lk.robot.newgenic.entity;

import javax.persistence.*;

@Entity
@Table(name = "variation_detail")
public class VariationProductDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variation_product_detail_id")
    private long variationProductDetailId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "variation_value_id")
    private VariationEntity variationEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    public VariationProductDetailEntity() {
    }

    public VariationProductDetailEntity(long variationProductDetailId,
                                        VariationEntity variationEntity,
                                        ProductEntity productEntity) {
        this.variationProductDetailId = variationProductDetailId;
        this.variationEntity = variationEntity;
        this.productEntity = productEntity;
    }

    public long getVariationProductDetailId() {
        return variationProductDetailId;
    }

    public void setVariationProductDetailId(long variationProductDetailId) {
        this.variationProductDetailId = variationProductDetailId;
    }

    public VariationEntity getVariationEntity() {
        return variationEntity;
    }

    public void setVariationEntity(VariationEntity variationEntity) {
        this.variationEntity = variationEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    @Override
    public String toString() {
        return "VariationProductDetailEntity{" +
                "variationProductDetailId=" + variationProductDetailId +
                ", variationEntity=" + variationEntity +
                ", productEntity=" + productEntity +
                '}';
    }
}
