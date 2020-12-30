package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    @Query("select p from ProductEntity p order by p.addedDate desc")
    List<ProductEntity> newArrivals(Pageable pageable);
}
