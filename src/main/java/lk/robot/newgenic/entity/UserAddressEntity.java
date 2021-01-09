package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_address")
public class UserAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_address_id")
    private long userAddress_id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String address;
    @Column
    private String district;
    @Column
    private String state;
    @Column
    private String mobile;
    @Column(name = "postal_code")
    private int postalCode;
    @Column
    private String type;
    @ManyToMany(mappedBy = "userAddress")
    private List<UserEntity> userEntityList  = new ArrayList<>();

    public UserAddressEntity() {
    }

    public UserAddressEntity(long userAddress_id,
                             String firstName,
                             String lastName,
                             String address,
                             String district,
                             String state,
                             String mobile,
                             int postalCode,
                             String type,
                             List<UserEntity> userEntityList) {
        this.userAddress_id = userAddress_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.district = district;
        this.state = state;
        this.mobile = mobile;
        this.postalCode = postalCode;
        this.type = type;
        this.userEntityList = userEntityList;
    }

    public long getUserAddress_id() {
        return userAddress_id;
    }

    public void setUserAddress_id(long userAddress_id) {
        this.userAddress_id = userAddress_id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public void setUserEntityList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
    }

    @Override
    public String toString() {
        return "UserAddressEntity{" +
                "userAddress_id=" + userAddress_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", mobile='" + mobile + '\'' +
                ", postalCode=" + postalCode +
                ", type='" + type + '\'' +
                ", userEntityList=" + userEntityList +
                '}';
    }
}
