package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.UserEntity;
import lk.robot.newgenic.entity.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<WishlistEntity,Long> {

    List<WishlistEntity> findByUserEntity(UserEntity userEntity);
}
