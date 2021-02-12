package lk.robot.newgenic.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_image")
public class ProductImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_id")
    private long productImageId;
    @Column
    private String url;
    @ManyToOne
    @JoinColumn(name = "fk_variation_detail_id")
    private VariationDetailEntity variationDetailEntity;

    public ProductImageEntity() {
    }

    public ProductImageEntity(long productImageId,
                              String url,
                              VariationDetailEntity variationDetailEntity) {
        this.productImageId = productImageId;
        this.url = url;
        this.variationDetailEntity = variationDetailEntity;
    }

    public long getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(long productImageId) {
        this.productImageId = productImageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public VariationDetailEntity getVariationDetailEntity() {
        return variationDetailEntity;
    }

    public void setVariationDetailEntity(VariationDetailEntity variationDetailEntity) {
        this.variationDetailEntity = variationDetailEntity;
    }

    @Override
    public String toString() {
        return "ProductImageEntity{" +
                "productImageId=" + productImageId +
                ", url='" + url + '\'' +
                ", variationDetailEntity=" + variationDetailEntity +
                '}';
    }
}
