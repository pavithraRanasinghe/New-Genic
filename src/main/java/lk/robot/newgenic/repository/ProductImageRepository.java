package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.ProductImageEntity;
import lk.robot.newgenic.entity.VariationDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity,Long> {

    List<ProductImageEntity> findByVariationDetailEntity(VariationDetailEntity variationDetailEntity);
}
