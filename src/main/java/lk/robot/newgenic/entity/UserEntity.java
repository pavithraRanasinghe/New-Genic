package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "profilePicture")
    private String profilePicture;
    @Column(nullable = false)
    private String gmail;
    @Column(nullable = false)
    private String mobile;
    @Column
    private Date dob;
    @Column
    private String password;
    @Column(name = "registered_date")
    private Date registeredDate;
    @ManyToMany
    @JoinTable(
            name = "user_address_details",
            joinColumns = {@JoinColumn (name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_address_id")}
    )
    private List<UserAddressEntity> userAddress = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(long userId, String firstName, String lastName, String profilePicture, String gmail, String mobile, Date dob, String password, Date registeredDate) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.gmail = gmail;
        this.mobile = mobile;
        this.dob = dob;
        this.password = password;
        this.registeredDate = registeredDate;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", gmail='" + gmail + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dob=" + dob +
                ", password='" + password + '\'' +
                ", registeredDate=" + registeredDate +
                '}';
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
}
