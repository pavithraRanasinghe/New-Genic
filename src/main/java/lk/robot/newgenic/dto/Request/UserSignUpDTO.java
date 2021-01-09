package lk.robot.newgenic.dto.Request;

public class UserSignUpDTO {

    private String firstName;
    private String lastName;
    private String userName;
    private String gmail;
    private String mobile;
    private String dob;
    private String password;
    private String profilePicture;
    private String role;

    public UserSignUpDTO(String firstName,
                         String lastName,
                         String userName,
                         String gmail,
                         String mobile,
                         String dob,
                         String password,
                         String profilePicture,
                         String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.gmail = gmail;
        this.mobile = mobile;
        this.dob = dob;
        this.password = password;
        this.profilePicture = profilePicture;
        this.role = role;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserSignUpDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", gmail='" + gmail + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dob='" + dob + '\'' +
                ", password='" + password + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
