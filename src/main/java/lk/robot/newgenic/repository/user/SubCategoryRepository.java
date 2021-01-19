package lk.robot.newgenic.repository.user;

import lk.robot.newgenic.entity.MainSubCategoryEntity;
import lk.robot.newgenic.entity.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity,Long> {

    List<SubCategoryEntity> findByMainSubCategoryEntity(MainSubCategoryEntity main);

    List<SubCategoryEntity> findAllByMainSubCategoryEntity(MainSubCategoryEntity mainSubCategoryEntity);
}
