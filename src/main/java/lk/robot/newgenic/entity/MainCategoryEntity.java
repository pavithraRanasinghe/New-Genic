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
    @ManyToOne
    @JoinColumn(name = "fk_admin_id")
    private AdminEntity adminEntity;


    public MainCategoryEntity() {
    }

    public MainCategoryEntity(
            long mainCategoryId,
            String mainCategoryName,
            String mainCategoryDescription,
            AdminEntity adminEntity) {
        this.mainCategoryId = mainCategoryId;
        this.mainCategoryName = mainCategoryName;
        this.mainCategoryDescription = mainCategoryDescription;
        this.adminEntity = adminEntity;
    }

    @Override
    public String toString() {
        return "MainCategoryEntity{" +
                "mainCategoryId=" + mainCategoryId +
                ", mainCategoryName='" + mainCategoryName + '\'' +
                ", mainCategoryDescription='" + mainCategoryDescription + '\'' +
                ", adminEntity=" + adminEntity +
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

    public AdminEntity getAdminEntity() {
        return adminEntity;
    }

    public void setAdminEntity(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }
}
