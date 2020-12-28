package lk.robot.newgenic.dto;

public class MainCategoryDTO {

    private long main_category_id;
    private String main_category_name;
    private String main_category_description;

    public MainCategoryDTO() {
    }

    public MainCategoryDTO(long main_category_id, String main_category_name, String main_category_description) {
        this.main_category_id = main_category_id;
        this.main_category_name = main_category_name;
        this.main_category_description = main_category_description;
    }

    @Override
    public String toString() {
        return "MainCategoryDTO{" +
                "main_category_id=" + main_category_id +
                ", main_category_name='" + main_category_name + '\'' +
                ", main_category_description='" + main_category_description + '\'' +
                '}';
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
}
