package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.OrderEntity;
import lk.robot.newgenic.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    OrderEntity findByUserEntityAndStatus(UserEntity userEntity,String status);
}
