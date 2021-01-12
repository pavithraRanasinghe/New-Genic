package lk.robot.newgenic.dto.Request;

public class BillingDetailDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String state;
    private String district;
    private String mobile;

    public BillingDetailDTO() {
    }

    public BillingDetailDTO(String firstName,
                            String lastName,
                            String address,
                            String state,
                            String district,
                            String mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.state = state;
        this.district = district;
        this.mobile = mobile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "BillingDetailDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
