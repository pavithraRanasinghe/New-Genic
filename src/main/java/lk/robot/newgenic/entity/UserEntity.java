package lk.robot.newgenic.entity;

import lk.robot.newgenic.enums.AuthenticationProvider;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    @Column(name = "user_uuid")
    private String userUuid;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "profile_picture")
    private String profilePicture;
    @Column(nullable = false)
    private String gmail;
    @Column
    private String mobile;
    @Column
    private Date dob;
    @Column
    private String role;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column(name = "registered_date")
    private Date registeredDate;
    @Column(name = "registered_time")
    private Time registeredTime;
    @Enumerated(EnumType.STRING)
    private AuthenticationProvider authenticationProvider;
    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    public UserEntity() {
    }

    public UserEntity(long userId,
                      String userUuid,
                      String firstName,
                      String lastName,
                      String profilePicture,
                      String gmail,
                      String mobile,
                      Date dob,
                      String role,
                      String username,
                      String password,
                      Date registeredDate,
                      Time registeredTime,
                      AuthenticationProvider authenticationProvider,
                      String resetPasswordToken) {
        this.userId = userId;
        this.userUuid = userUuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.gmail = gmail;
        this.mobile = mobile;
        this.dob = dob;
        this.role = role;
        this.username = username;
        this.password = password;
        this.registeredDate = registeredDate;
        this.registeredTime = registeredTime;
        this.authenticationProvider = authenticationProvider;
        this.resetPasswordToken = resetPasswordToken;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }

    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", userUuid='" + userUuid + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", gmail='" + gmail + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dob=" + dob +
                ", role='" + role + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", registeredDate=" + registeredDate +
                ", registeredTime=" + registeredTime +
                ", authenticationProvider=" + authenticationProvider +
                ", resetPasswordToken='" + resetPasswordToken + '\'' +
                '}';
    }
}
