package lk.robot.newgenic.repository.user;

import lk.robot.newgenic.entity.UserAddressDetailEntity;
import lk.robot.newgenic.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAddressDetailRepository extends JpaRepository<UserAddressDetailEntity, Long> {

    List<UserAddressDetailEntity> findByUserEntity(UserEntity userEntity);
}
