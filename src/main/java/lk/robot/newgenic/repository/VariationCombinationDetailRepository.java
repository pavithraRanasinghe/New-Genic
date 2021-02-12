package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.VariationCombinationDetailEntity;
import lk.robot.newgenic.entity.VariationDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VariationCombinationDetailRepository extends JpaRepository<VariationCombinationDetailEntity,Long> {

    List<VariationCombinationDetailEntity> findByVariationDetailEntity(VariationDetailEntity variationDetailEntity);
}
