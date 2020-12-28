package lk.robot.newgenic.dto;

public class SubCategoryDTO {

    private long sub_category_id;
    private String sub_category_name;
    private String sub_category_description;

    public SubCategoryDTO() {
    }

    public SubCategoryDTO(long sub_category_id, String sub_category_name, String sub_category_description) {
        this.sub_category_id = sub_category_id;
        this.sub_category_name = sub_category_name;
        this.sub_category_description = sub_category_description;
    }

    @Override
    public String toString() {
        return "SubCategoryDTO{" +
                "sub_category_id=" + sub_category_id +
                ", sub_category_name='" + sub_category_name + '\'' +
                ", sub_category_description='" + sub_category_description + '\'' +
                '}';
    }

    public long getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(long sub_category_id) {
        this.sub_category_id = sub_category_id;
    }

    public String getSub_category_name() {
        return sub_category_name;
    }

    public void setSub_category_name(String sub_category_name) {
        this.sub_category_name = sub_category_name;
    }

    public String getSub_category_description() {
        return sub_category_description;
    }

    public void setSub_category_description(String sub_category_description) {
        this.sub_category_description = sub_category_description;
    }
}
