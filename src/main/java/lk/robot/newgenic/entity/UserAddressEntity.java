package lk.robot.newgenic.entity;

import javax.persistence.*;

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
    private String city;
    @Column
    private String mobile;
    @Column(name = "postal_code")
    private int postalCode;
    @Column
    private String type;

    public UserAddressEntity() {
    }

    public UserAddressEntity(long userAddress_id,
                             String firstName,
                             String lastName,
                             String address,
                             String district,
                             String city,
                             String mobile,
                             int postalCode,
                             String type) {
        this.userAddress_id = userAddress_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.district = district;
        this.city = city;
        this.mobile = mobile;
        this.postalCode = postalCode;
        this.type = type;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    @Override
    public String toString() {
        return "UserAddressEntity{" +
                "userAddress_id=" + userAddress_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", mobile='" + mobile + '\'' +
                ", postalCode=" + postalCode +
                ", type='" + type + '\'' +
                '}';
    }
}
