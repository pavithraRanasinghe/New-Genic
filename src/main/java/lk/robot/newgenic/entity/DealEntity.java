package lk.robot.newgenic.entity;

import lk.robot.newgenic.enums.DealStatus;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "deal")
public class DealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deal_id")
    private long dealId;
    @Column
    private String name;
    @Column
    private String description;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "start_time")
    private Time startTime;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "end_time")
    private Time endTime;
    @Column
    private double discount;
    @Column
    @Enumerated(EnumType.STRING)
    private DealStatus dealStatus;
    @ManyToOne
    @JoinColumn(name = "fk_admin_id")
    private AdminEntity adminEntity;

    public DealEntity() {
    }

    public DealEntity(long dealId,
                      String name,
                      String description,
                      Date startDate,
                      Time startTime,
                      Date endDate,
                      Time endTime,
                      double discount,
                      DealStatus dealStatus,
                      AdminEntity adminEntity) {
        this.dealId = dealId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.discount = discount;
        this.dealStatus = dealStatus;
        this.adminEntity = adminEntity;
    }

    public long getDealId() {
        return dealId;
    }

    public void setDealId(long dealId) {
        this.dealId = dealId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public DealStatus getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }

    public AdminEntity getAdminEntity() {
        return adminEntity;
    }

    public void setAdminEntity(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }

    @Override
    public String toString() {
        return "DealEntity{" +
                "dealId=" + dealId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", startTime=" + startTime +
                ", endDate=" + endDate +
                ", endTime=" + endTime +
                ", discount=" + discount +
                ", dealStatus=" + dealStatus +
                ", adminEntity=" + adminEntity +
                '}';
    }
}
