package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.UserAddressEntity;
import lk.robot.newgenic.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {

}
