package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.entity.VariationProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VariationProductDetailRepository extends JpaRepository<VariationProductDetailEntity, Long> {

    List<VariationProductDetailEntity> findByProductEntity(ProductEntity productEntity);
}
