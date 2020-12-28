package lk.robot.newgenic.dto;

public class MainSubCategory {

    private long main_sub_category_id;
    private String main_sub_category_name;
    private String main_sub_category_description;

    public MainSubCategory() {
    }

    public MainSubCategory(long main_sub_category_id, String main_sub_category_name, String main_sub_category_description) {
        this.main_sub_category_id = main_sub_category_id;
        this.main_sub_category_name = main_sub_category_name;
        this.main_sub_category_description = main_sub_category_description;
    }

    @Override
    public String toString() {
        return "MainSubCategory{" +
                "main_sub_category_id=" + main_sub_category_id +
                ", main_sub_category_name='" + main_sub_category_name + '\'' +
                ", main_sub_category_description='" + main_sub_category_description + '\'' +
                '}';
    }

    public long getMain_sub_category_id() {
        return main_sub_category_id;
    }

    public void setMain_sub_category_id(long main_sub_category_id) {
        this.main_sub_category_id = main_sub_category_id;
    }

    public String getMain_sub_category_name() {
        return main_sub_category_name;
    }

    public void setMain_sub_category_name(String main_sub_category_name) {
        this.main_sub_category_name = main_sub_category_name;
    }

    public String getMain_sub_category_description() {
        return main_sub_category_description;
    }

    public void setMain_sub_category_description(String main_sub_category_description) {
        this.main_sub_category_description = main_sub_category_description;
    }
}
