package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delivery")
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private long deliveryId;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String gmail;
    @Column
    private String mobile;
    @Column
    private String website;
    @Column
    private double cost_per_unit;
    @Column(name = "registration_date")
    private Date registrationDate;
    @Column(name = "registration_time")
    private Date registrationTime;
    @Column(name = "registration_number")
    private String registrationNumber;
    @ManyToOne
    @JoinColumn(name = "fk_admin_id")
    private AdminEntity adminEntity;

    public DeliveryEntity() {
    }

    public DeliveryEntity(long deliveryId, String name, String address, String gmail, String mobile, String website, double cost_per_unit, Date registrationDate, Date registrationTime, String registrationNumber, AdminEntity adminEntity) {
        this.deliveryId = deliveryId;
        this.name = name;
        this.address = address;
        this.gmail = gmail;
        this.mobile = mobile;
        this.website = website;
        this.cost_per_unit = cost_per_unit;
        this.registrationDate = registrationDate;
        this.registrationTime = registrationTime;
        this.registrationNumber = registrationNumber;
        this.adminEntity = adminEntity;
    }

    public long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public double getCost_per_unit() {
        return cost_per_unit;
    }

    public void setCost_per_unit(double cost_per_unit) {
        this.cost_per_unit = cost_per_unit;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public AdminEntity getAdminEntity() {
        return adminEntity;
    }

    public void setAdminEntity(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }

    @Override
    public String toString() {
        return "DeliveryEntity{" +
                "deliveryId=" + deliveryId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gmail='" + gmail + '\'' +
                ", mobile='" + mobile + '\'' +
                ", website='" + website + '\'' +
                ", cost_per_unit=" + cost_per_unit +
                ", registrationDate=" + registrationDate +
                ", registrationTime=" + registrationTime +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", adminEntity=" + adminEntity +
                '}';
    }
}
