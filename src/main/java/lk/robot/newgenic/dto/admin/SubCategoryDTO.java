package lk.robot.newgenic.dto.admin;

public class SubCategoryDTO {

    private String subCategoryName;
    private String subCategoryDescription;

    public SubCategoryDTO() {
    }

    public SubCategoryDTO(String subCategoryName,
                          String subCategoryDescription) {
        this.subCategoryName = subCategoryName;
        this.subCategoryDescription = subCategoryDescription;
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

    @Override
    public String toString() {
        return "SubCategoryDTO{" +
                "subCategoryName='" + subCategoryName + '\'' +
                ", subCategoryDescription='" + subCategoryDescription + '\'' +
                '}';
    }
}
