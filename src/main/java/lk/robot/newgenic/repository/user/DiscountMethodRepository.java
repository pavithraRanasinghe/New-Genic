package lk.robot.newgenic.repository.user;

import lk.robot.newgenic.entity.DiscountMethodEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiscountMethodRepository extends JpaRepository<DiscountMethodEntity, Long> {

    @Query(value = "select * from discount_method where active = true order by discount_method_id desc limit 1",
    nativeQuery = true)
    DiscountMethodEntity getDiscountMethod();
}
