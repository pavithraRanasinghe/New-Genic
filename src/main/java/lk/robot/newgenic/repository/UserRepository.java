package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.username=?1")
    Optional<UserEntity> validateUser(String username);

    UserEntity findByGmail(String gmail);

    UserEntity findByResetPasswordToken(String token);

    Optional<UserEntity> findByUserUuid(String uuid);
}
