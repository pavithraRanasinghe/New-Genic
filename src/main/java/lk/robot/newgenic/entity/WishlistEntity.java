package lk.robot.newgenic.entity;

import javax.persistence.*;

@Entity
@Table(name = "wishlist")
public class WishlistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private long wishlistId;
    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "fk_product_id")
    private ProductEntity productEntity;

    public WishlistEntity() {
    }

    public WishlistEntity(long wishlistId, UserEntity userEntity, ProductEntity productEntity) {
        this.wishlistId = wishlistId;
        this.userEntity = userEntity;
        this.productEntity = productEntity;
    }

    public long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(long wishlistId) {
        this.wishlistId = wishlistId;
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
        return "WishlistEntity{" +
                "wishlistId=" + wishlistId +
                ", userEntity=" + userEntity +
                ", productEntity=" + productEntity +
                '}';
    }
}
