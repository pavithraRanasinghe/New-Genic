package lk.robot.newgenic.dto.user.response;

import java.util.List;

public class CategoryResponseDTO {

    private long main_category_id;
    private String main_category_name;
    private String main_category_description;
    private List<MainSubCategoryResponseDTO> mainSubCategoryResponseDTO;

    public CategoryResponseDTO() {
    }

    public CategoryResponseDTO(long main_category_id, String main_category_name, String main_category_description, List<MainSubCategoryResponseDTO> mainSubCategoryResponseDTO) {
        this.main_category_id = main_category_id;
        this.main_category_name = main_category_name;
        this.main_category_description = main_category_description;
        this.mainSubCategoryResponseDTO = mainSubCategoryResponseDTO;
    }

    public long getMain_category_id() {
        return main_category_id;
    }

    public void setMain_category_id(long main_category_id) {
        this.main_category_id = main_category_id;
    }

    public String getMain_category_name() {
        return main_category_name;
    }

    public void setMain_category_name(String main_category_name) {
        this.main_category_name = main_category_name;
    }

    public String getMain_category_description() {
        return main_category_description;
    }

    public void setMain_category_description(String main_category_description) {
        this.main_category_description = main_category_description;
    }

    public List<MainSubCategoryResponseDTO> getMainSubCategoryResponseDTO() {
        return mainSubCategoryResponseDTO;
    }

    public void setMainSubCategoryResponseDTO(List<MainSubCategoryResponseDTO> mainSubCategoryResponseDTO) {
        this.mainSubCategoryResponseDTO = mainSubCategoryResponseDTO;
    }

    @Override
    public String toString() {
        return "CategoryResponseDTO{" +
                "main_category_id=" + main_category_id +
                ", main_category_name='" + main_category_name + '\'' +
                ", main_category_description='" + main_category_description + '\'' +
                ", mainSubCategoryResponseDTO=" + mainSubCategoryResponseDTO +
                '}';
    }
}
