package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.entity.QuestionAreaEntity;
import lk.robot.newgenic.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAreaRepository extends JpaRepository<QuestionAreaEntity,Long> {

    QuestionAreaEntity findByProductEntityAndUserEntity(ProductEntity productEntity, UserEntity userEntity);
}
