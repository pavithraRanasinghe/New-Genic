package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.ProductFeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<ProductFeedbackEntity,Long> {

    List<ProductFeedbackEntity> findByProductEntity();

}
