package lk.robot.newgenic.dto.request;

public class UserDetailDTO {

    private String firstName;
    private String lastName;
    private String gmail;
    private String mobile;
    private String dob;
    private String address;
    private String district;
    private String city;
    private int postalCode;

    public UserDetailDTO() {
    }

    public UserDetailDTO(String firstName,
                         String lastName,
                         String gmail,
                         String mobile,
                         String dob,
                         String address,
                         String district,
                         String city,
                         int postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gmail = gmail;
        this.mobile = mobile;
        this.dob = dob;
        this.address = address;
        this.district = district;
        this.city = city;
        this.postalCode = postalCode;
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

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "UserDetailDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gmail='" + gmail + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dob='" + dob + '\'' +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
