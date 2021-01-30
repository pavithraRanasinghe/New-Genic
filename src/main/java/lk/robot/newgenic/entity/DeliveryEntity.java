package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

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
    @Column(name = "registration_date")
    private Date registrationDate;
    @Column(name = "registration_time")
    private Time registrationTime;
    @Column(name = "registration_number")
    private String registrationNumber;
    @Column
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "fk_admin_id")
    private AdminEntity adminEntity;
    @OneToMany(mappedBy = "deliveryEntity",fetch = FetchType.LAZY)
    private List<DeliveryCostEntity> costEntityList;

    public DeliveryEntity() {
    }

    public DeliveryEntity(long deliveryId,
                          String name,
                          String address,
                          String gmail,
                          String mobile,
                          String website,
                          Date registrationDate,
                          Time registrationTime,
                          String registrationNumber,
                          boolean active,
                          AdminEntity adminEntity) {
        this.deliveryId = deliveryId;
        this.name = name;
        this.address = address;
        this.gmail = gmail;
        this.mobile = mobile;
        this.website = website;
        this.registrationDate = registrationDate;
        this.registrationTime = registrationTime;
        this.registrationNumber = registrationNumber;
        this.active = active;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Time getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Time registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public AdminEntity getAdminEntity() {
        return adminEntity;
    }

    public void setAdminEntity(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }

    public List<DeliveryCostEntity> getCostEntityList() {
        return costEntityList;
    }

    public void setCostEntityList(List<DeliveryCostEntity> costEntityList) {
        this.costEntityList = costEntityList;
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
                ", registrationDate=" + registrationDate +
                ", registrationTime=" + registrationTime +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", active=" + active +
                ", adminEntity=" + adminEntity +
                ", costEntityList=" + costEntityList +
                '}';
    }
}
