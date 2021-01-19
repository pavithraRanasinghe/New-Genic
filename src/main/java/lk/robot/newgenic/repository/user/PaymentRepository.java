package lk.robot.newgenic.repository.user;

import lk.robot.newgenic.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {
}
