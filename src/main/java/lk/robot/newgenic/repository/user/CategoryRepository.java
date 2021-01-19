package lk.robot.newgenic.repository.user;

import lk.robot.newgenic.entity.MainCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<MainCategoryEntity,Long> {
}
