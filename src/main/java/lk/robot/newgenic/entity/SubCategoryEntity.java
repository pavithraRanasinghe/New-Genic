package lk.robot.newgenic.entity;

import javax.persistence.*;

@Entity
@Table(name = "sub_category")
public class SubCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_category_id")
    private long subCategoryId;
    @Column(nullable = false)
    private String subCategoryName;
    @Column
    private String subCategoryDescription;
    @ManyToOne
    @JoinColumn(name = "fk_main_sub_category_id")
    private MainSubCategoryEntity mainSubCategoryEntity;

    public SubCategoryEntity() {
    }

    public SubCategoryEntity(
            long subCategoryId,
            String subCategoryName,
            String subCategoryDescription,
            MainSubCategoryEntity mainSubCategoryEntity) {
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.subCategoryDescription = subCategoryDescription;
        this.mainSubCategoryEntity = mainSubCategoryEntity;
    }

    @Override
    public String toString() {
        return "SubCategoryEntity{" +
                "subCategoryId=" + subCategoryId +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", subCategoryDescription='" + subCategoryDescription + '\'' +
                ", mainSubCategory=" + mainSubCategoryEntity +
                '}';
    }

    public long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getSubCategoryDescription() {
        return subCategoryDescription;
    }

    public void setSubCategoryDescription(String subCategoryDescription) {
        this.subCategoryDescription = subCategoryDescription;
    }

    public MainSubCategoryEntity getMainSubCategory() {
        return mainSubCategoryEntity;
    }

    public void setMainSubCategory(MainSubCategoryEntity mainSubCategoryEntity) {
        this.mainSubCategoryEntity = mainSubCategoryEntity;
    }
}
