package lk.robot.newgenic.dto.admin.request;

import lk.robot.newgenic.dto.admin.SubCategoryDTO;

import java.util.List;

public class CategoryRequestDTO {

    private long mainCategoryId;
    private String mainSubCategoryName;
    private String mainSubCategoryDescription;
    private List<SubCategoryDTO> subCategoryList;

    public CategoryRequestDTO() {
    }

    public CategoryRequestDTO(long mainCategoryId,
                              String mainSubCategoryName,
                              String mainSubCategoryDescription,
                              List<SubCategoryDTO> subCategoryList) {
        this.mainCategoryId = mainCategoryId;
        this.mainSubCategoryName = mainSubCategoryName;
        this.mainSubCategoryDescription = mainSubCategoryDescription;
        this.subCategoryList = subCategoryList;
    }

    public long getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(long mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public String getMainSubCategoryName() {
        return mainSubCategoryName;
    }

    public void setMainSubCategoryName(String mainSubCategoryName) {
        this.mainSubCategoryName = mainSubCategoryName;
    }

    public String getMainSubCategoryDescription() {
        return mainSubCategoryDescription;
    }

    public void setMainSubCategoryDescription(String mainSubCategoryDescription) {
        this.mainSubCategoryDescription = mainSubCategoryDescription;
    }

    public List<SubCategoryDTO> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<SubCategoryDTO> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    @Override
    public String toString() {
        return "CategoryRequestDTO{" +
                "mainCategoryId=" + mainCategoryId +
                ", mainSubCategoryName='" + mainSubCategoryName + '\'' +
                ", mainSubCategoryDescription='" + mainSubCategoryDescription + '\'' +
                ", subCategoryList=" + subCategoryList +
                '}';
    }
}
