package lk.robot.newgenic.entity;

import javax.persistence.*;

@Entity
@Table(name = "item_feedback")
public class ProductFeedbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_feedback_id")
    private long itemFeedbackId;
    @Column(nullable = false,length = 4)
    private int rate;
    @Column
    private String message;
    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "fk_product_id")
    private ProductEntity productEntity;

    public ProductFeedbackEntity() {
    }

    public ProductFeedbackEntity(long itemFeedbackId, int rate, String message, UserEntity userEntity, ProductEntity productEntity) {
        this.itemFeedbackId = itemFeedbackId;
        this.rate = rate;
        this.message = message;
        this.userEntity = userEntity;
        this.productEntity = productEntity;
    }

    @Override
    public String toString() {
        return "ItemFeedbackEntity{" +
                "itemFeedbackId=" + itemFeedbackId +
                ", rate=" + rate +
                ", message='" + message + '\'' +
                ", userEntity=" + userEntity +
                ", productEntity=" + productEntity +
                '}';
    }

    public long getItemFeedbackId() {
        return itemFeedbackId;
    }

    public void setItemFeedbackId(long itemFeedbackId) {
        this.itemFeedbackId = itemFeedbackId;
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
}
