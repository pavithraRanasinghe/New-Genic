package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_feedback")
public class ProductFeedbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_feedback_id")
    private long productFeedbackId;
    @Column(nullable = false,length = 4)
    private int rate;
    @Column
    private String message;
    @Column(name = "added_date")
    private Date addedDate;
    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "fk_product_id")
    private ProductEntity productEntity;

    public ProductFeedbackEntity() {
    }

    public ProductFeedbackEntity(long productFeedbackId, int rate, String message, Date addedDate, UserEntity userEntity, ProductEntity productEntity) {
        this.productFeedbackId = productFeedbackId;
        this.rate = rate;
        this.message = message;
        this.addedDate = addedDate;
        this.userEntity = userEntity;
        this.productEntity = productEntity;
    }

    public long getProductFeedbackId() {
        return productFeedbackId;
    }

    public void setProductFeedbackId(long productFeedbackId) {
        this.productFeedbackId = productFeedbackId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    @Override
    public String toString() {
        return "ProductFeedbackEntity{" +
                "productFeedbackId=" + productFeedbackId +
                ", rate=" + rate +
                ", message='" + message + '\'' +
                ", addedDate=" + addedDate +
                ", userEntity=" + userEntity +
                ", productEntity=" + productEntity +
                '}';
    }
}
