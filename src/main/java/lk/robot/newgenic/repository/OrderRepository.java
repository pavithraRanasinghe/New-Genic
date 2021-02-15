package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.OrderEntity;
import lk.robot.newgenic.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    OrderEntity findByUserEntityAndStatus(UserEntity userEntity,String status);

    List<OrderEntity> findByUserEntity(UserEntity userEntity);

    Optional<OrderEntity> findByOrderUuid(String uuid);
}
