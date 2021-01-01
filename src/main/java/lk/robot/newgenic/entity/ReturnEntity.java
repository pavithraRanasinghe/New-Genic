package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "return_request")
public class ReturnEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "return_request_id")
    private long returnRequestId;
    @Column
    private String reason;
    @Column(name = "request_date")
    private Date requestDate;
    @Column
    private String img;
    @OneToOne(mappedBy = "returnEntity")
    private OrderDetailEntity orderDetailEntity;

    public ReturnEntity() {
    }

    public ReturnEntity(long returnRequestId, String reason, Date requestDate, String img) {
        this.returnRequestId = returnRequestId;
        this.reason = reason;
        this.requestDate = requestDate;
        this.img = img;
    }

    public long getReturnRequestId() {
        return returnRequestId;
    }

    public void setReturnRequestId(long returnRequestId) {
        this.returnRequestId = returnRequestId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "ReturnEntity{" +
                "returnRequestId=" + returnRequestId +
                ", reason='" + reason + '\'' +
                ", requestDate=" + requestDate +
                ", img='" + img + '\'' +
                '}';
    }
}
