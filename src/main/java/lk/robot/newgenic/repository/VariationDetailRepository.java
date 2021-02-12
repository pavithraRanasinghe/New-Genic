package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.VariationDetailEntity;
import lk.robot.newgenic.entity.VariationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VariationDetailRepository extends JpaRepository<VariationDetailEntity,Long> {

    List<VariationDetailEntity> findByVariationEntity(VariationEntity variationEntity);
}
