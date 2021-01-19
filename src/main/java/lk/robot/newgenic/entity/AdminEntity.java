package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "admin")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private long adminId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true)
    private String username;
    @Column
    private String nic;
    @Column
    private String gender;
    @Column
    private String gmail;
    @Column
    private String mobile;
    @Column(name = "profile_picture")
    private String profilePicture;
    @Column
    private String password;
    @Column(name = "registered_date")
    private Date registeredDate;
    @Column(name = "registered_time")
    private Time registeredTime;

    public AdminEntity() {
    }

    public AdminEntity(long adminId,
                       String firstName,
                       String lastName,
                       String username,
                       String nic,
                       String gender,
                       String gmail,
                       String mobile,
                       String profilePicture,
                       String password,
                       Date registeredDate,
                       Time registeredTime,
                       String role) {
        this.adminId = adminId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.nic = nic;
        this.gender = gender;
        this.gmail = gmail;
        this.mobile = mobile;
        this.profilePicture = profilePicture;
        this.password = password;
        this.registeredDate = registeredDate;
        this.registeredTime = registeredTime;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return "AdminEntity{" +
                "adminId=" + adminId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", nic='" + nic + '\'' +
                ", gender='" + gender + '\'' +
                ", gmail='" + gmail + '\'' +
                ", mobile='" + mobile + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", password='" + password + '\'' +
                ", registeredDate=" + registeredDate +
                ", registeredTime=" + registeredTime +
                '}';
    }
}
