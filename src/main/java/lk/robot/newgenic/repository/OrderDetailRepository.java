package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.CombinationEntity;
import lk.robot.newgenic.entity.OrderDetailEntity;
import lk.robot.newgenic.entity.OrderEntity;
import lk.robot.newgenic.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity,Long> {

    OrderDetailEntity findByOrderEntityAndCombinationEntity(OrderEntity orderEntity, CombinationEntity combinationEntity);

    List<OrderDetailEntity> findByOrderEntity(OrderEntity orderEntity);

}
