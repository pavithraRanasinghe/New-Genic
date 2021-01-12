package lk.robot.newgenic.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_address_details")
public class UserAddressDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userAddressDetailId;
    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "fk_user_address_id")
    private UserAddressEntity userAddressEntity;

    public UserAddressDetailEntity() {
    }

    public UserAddressDetailEntity(long userAddressDetailId,
                                   UserEntity userEntity,
                                   UserAddressEntity userAddressEntity) {
        this.userAddressDetailId = userAddressDetailId;
        this.userEntity = userEntity;
        this.userAddressEntity = userAddressEntity;
    }

    public long getUserAddressDetailId() {
        return userAddressDetailId;
    }

    public void setUserAddressDetailId(long userAddressDetailId) {
        this.userAddressDetailId = userAddressDetailId;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UserAddressEntity getUserAddressEntity() {
        return userAddressEntity;
    }

    public void setUserAddressEntity(UserAddressEntity userAddressEntity) {
        this.userAddressEntity = userAddressEntity;
    }

    @Override
    public String toString() {
        return "UserAddressDetail{" +
                "userAddressDetailId=" + userAddressDetailId +
                ", userEntity=" + userEntity +
                ", userAddressEntity=" + userAddressEntity +
                '}';
    }
}
