package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.DealEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<DealEntity, Long> {

    DealEntity findByDealStatus(String status);
}
