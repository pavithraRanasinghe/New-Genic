package lk.robot.newgenic.dto.Request;

public class ShippingDetailDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String state;
    private String district;
    private int postalCode;
    private String mobile;

    public ShippingDetailDTO() {
    }

    public ShippingDetailDTO(String firstName,
                             String lastName,
                             String address,
                             String state,
                             String district,
                             int postalCode,
                             String mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.state = state;
        this.district = district;
        this.postalCode = postalCode;
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

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "ShippingDetailDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", postalCode=" + postalCode +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
