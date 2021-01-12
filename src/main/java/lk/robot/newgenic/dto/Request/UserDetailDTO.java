package lk.robot.newgenic.dto.Request;

public class UserDetailDTO {

    private String firstName;
    private String lastName;
    private String gmail;
    private String mobile;
    private String dob;
    private String profilePicture;
    private String address;
    private String district;
    private String state;
    private int postalCode;

    public UserDetailDTO() {
    }

    public UserDetailDTO(String firstName,
                         String lastName,
                         String gmail,
                         String mobile,
                         String dob,
                         String profilePicture,
                         String address,
                         String district,
                         String state,
                         int postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gmail = gmail;
        this.mobile = mobile;
        this.dob = dob;
        this.profilePicture = profilePicture;
        this.address = address;
        this.district = district;
        this.state = state;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
                ", profilePicture='" + profilePicture + '\'' +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
