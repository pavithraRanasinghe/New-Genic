package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.DiscountMethodEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiscountMethodRepository extends JpaRepository<DiscountMethodEntity, Long> {

    @Query("select d from DiscountMethodEntity d order by d.discountMethodId desc")
    DiscountMethodEntity getDiscountMethod(Pageable pageable);
}
