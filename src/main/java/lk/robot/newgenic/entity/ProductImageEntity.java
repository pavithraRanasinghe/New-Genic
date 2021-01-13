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
    @JoinColumn(name = "fk_product_id")
    private ProductEntity productEntity;

    public ProductImageEntity() {
    }

    public ProductImageEntity(long productImageId,
                              String url,
                              ProductEntity productEntity) {
        this.productImageId = productImageId;
        this.url = url;
        this.productEntity = productEntity;
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

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    @Override
    public String toString() {
        return "ProductImageEntity{" +
                "productImageId=" + productImageId +
                ", url='" + url + '\'' +
                ", productEntity=" + productEntity +
                '}';
    }
}
