package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.MainCategoryEntity;
import lk.robot.newgenic.entity.MainSubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainSubCategoryRepository extends JpaRepository<MainSubCategoryEntity,Long> {

    List<MainSubCategoryEntity> findByMainCategoryEntity(MainCategoryEntity mainCategory);

}
