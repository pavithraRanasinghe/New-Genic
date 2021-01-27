package lk.robot.newgenic.dto;

import javax.persistence.Column;
import java.sql.Date;
import java.sql.Time;

public class UserDTO {

    private long userId;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String gmail;
    private String mobile;
    private Date dob;
    private String username;
    private Date registeredDate;
    private Time registeredTime;

    public UserDTO() {
    }

    public UserDTO(long userId,
                   String firstName,
                   String lastName,
                   String profilePicture,
                   String gmail,
                   String mobile,
                   Date dob,
                   String username,
                   Date registeredDate,
                   Time registeredTime) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.gmail = gmail;
        this.mobile = mobile;
        this.dob = dob;
        this.username = username;
        this.registeredDate = registeredDate;
        this.registeredTime = registeredTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Time getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(Time registeredTime) {
        this.registeredTime = registeredTime;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", gmail='" + gmail + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dob=" + dob +
                ", username='" + username + '\'' +
                ", registeredDate=" + registeredDate +
                ", registeredTime=" + registeredTime +
                '}';
    }
}
