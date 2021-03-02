package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.DealEntity;
import lk.robot.newgenic.enums.DealStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<DealEntity, Long> {

    DealEntity findByDealStatus(DealStatus status);
}
