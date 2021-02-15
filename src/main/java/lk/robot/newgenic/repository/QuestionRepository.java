package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.QuestionAreaEntity;
import lk.robot.newgenic.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    List<QuestionEntity> findByQuestionAreaEntityAndApproveTrue(QuestionAreaEntity questionAreaEntity);

}
