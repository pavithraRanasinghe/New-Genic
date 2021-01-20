package lk.robot.newgenic.repository.admin;

import lk.robot.newgenic.entity.MainCategoryEntity;
import lk.robot.newgenic.entity.MainSubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainSubCategoryRepository extends JpaRepository<MainSubCategoryEntity,Long> {

    MainSubCategoryEntity findByMainSubCategoryNameAndMainCategoryEntity(String name, MainCategoryEntity mainCategory);
}
