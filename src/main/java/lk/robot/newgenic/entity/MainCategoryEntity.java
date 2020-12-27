package lk.robot.newgenic.entity;

import javax.persistence.*;

@Entity
@Table(name = "main_category")
public class MainCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_category_id")
    private long mainCategoryId;
    @Column(nullable = false)
    private String mainCategoryName;
    @Column
    private String mainCategoryDescription;

    public MainCategoryEntity() {
    }

    public MainCategoryEntity(
            long mainCategoryId,
            String mainCategoryName,
            String mainCategoryDescription) {
        this.mainCategoryId = mainCategoryId;
        this.mainCategoryName = mainCategoryName;
        this.mainCategoryDescription = mainCategoryDescription;
    }

    @Override
    public String toString() {
        return "MainCategoryEntity{" +
                "mainCategoryId=" + mainCategoryId +
                ", mainCategoryName='" + mainCategoryName + '\'' +
                ", mainCategoryDescription='" + mainCategoryDescription + '\'' +
                '}';
    }

    public long getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(long mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public String getMainCategoryDescription() {
        return mainCategoryDescription;
    }

    public void setMainCategoryDescription(String mainCategoryDescription) {
        this.mainCategoryDescription = mainCategoryDescription;
    }
}
