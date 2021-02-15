package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long productId;
    @Column(name = "product_code", nullable = false)
    private String productCode;
    @Column(name = "product_uuid")
    private String uuid;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column
    private String brand;
    @Column(name = "added_date", nullable = false)
    private Date addedDate;
    @Column(name = "added_time", nullable = false)
    private Time addedTime;
    @Column(name = "update_date", nullable = false)
    private Date updateDate;
    @Column(name = "update_time", nullable = false)
    private Time updateTime;
    @Column(nullable = false)
    private boolean active;
    @Column(name = "is_free_shipping")
    private boolean isFreeShipping;
    @ManyToOne
    @JoinColumn(name = "fk_sub_category_id")
    private SubCategoryEntity subCategoryEntity;

    @ManyToOne
    @JoinColumn(name = "fk_deal_id")
    private DealEntity dealEntity;


    public ProductEntity() {
    }

    public ProductEntity(
            long productId,
            String productCode,
            String uuid,
            String name,
            String description,
            String brand,
            Date addedDate,
            Time addedTime,
            Date updateDate,
            Time updateTime,
            boolean active,
            boolean isFreeShipping,
            SubCategoryEntity subCategoryEntity,
            DealEntity dealEntity) {
        this.productId = productId;
        this.productCode = productCode;
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.addedDate = addedDate;
        this.addedTime = addedTime;
        this.updateDate = updateDate;
        this.updateTime = updateTime;
        this.active = active;
        this.isFreeShipping = isFreeShipping;
        this.subCategoryEntity = subCategoryEntity;
        this.dealEntity = dealEntity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Time getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Time addedTime) {
        this.addedTime = addedTime;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Time getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Time updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isFreeShipping() {
        return isFreeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        isFreeShipping = freeShipping;
    }

    public SubCategoryEntity getSubCategoryEntity() {
        return subCategoryEntity;
    }

    public void setSubCategoryEntity(SubCategoryEntity subCategoryEntity) {
        this.subCategoryEntity = subCategoryEntity;
    }

    public DealEntity getDealEntity() {
        return dealEntity;
    }

    public void setDealEntity(DealEntity dealEntity) {
        this.dealEntity = dealEntity;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "productId=" + productId +
                ", productCode='" + productCode + '\'' +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", addedDate=" + addedDate +
                ", addedTime=" + addedTime +
                ", updateDate=" + updateDate +
                ", updateTime=" + updateTime +
                ", active=" + active +
                ", isFreeShipping=" + isFreeShipping +
                ", subCategoryEntity=" + subCategoryEntity +
                ", dealEntity=" + dealEntity +
                '}';
    }
}
