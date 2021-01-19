package lk.robot.newgenic.dto.admin.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRequestDTO {

    private String firstName;
    private String lastName;
    @NotNull(message = "User name required")
    private String userName;
    private String nic;
    private String gender;
    @NotNull(message = "Email required.cannot be blank")
    @Email(message = "Please input valid email address")
    private String gmail;
    private String mobile;
    private String profilePicture;
    @NotNull(message = "Password shouldn't empty")
    private String password;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String firstName,
                          String lastName,
                          @NotNull(message = "User name required")
                                  String userName,
                          String nic,
                          String gender,
                          @NotNull(message = "Email required.cannot be blank")
                          @Email(message = "Please input valid email address")
                                  String gmail,
                          String mobile,
                          String profilePicture,
                          @NotNull(message = "Password shouldn't empty")
                                  String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.nic = nic;
        this.gender = gender;
        this.gmail = gmail;
        this.mobile = mobile;
        this.profilePicture = profilePicture;
        this.password = password;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", nic='" + nic + '\'' +
                ", gender='" + gender + '\'' +
                ", gmail='" + gmail + '\'' +
                ", mobile='" + mobile + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
