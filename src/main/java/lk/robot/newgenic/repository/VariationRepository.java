package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.entity.VariationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VariationRepository extends JpaRepository<VariationEntity, Long> {

    List<VariationEntity> findByProductEntity(ProductEntity productEntity);
}
