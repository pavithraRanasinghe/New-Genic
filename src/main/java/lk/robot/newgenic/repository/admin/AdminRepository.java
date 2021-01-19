package lk.robot.newgenic.repository.admin;

import lk.robot.newgenic.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    AdminEntity findByUsername(String userName);
}
