package lk.robot.newgenic.repository;

import lk.robot.newgenic.entity.DealEntity;
import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.entity.SubCategoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    @Query("select p from ProductEntity p where p.active=true order by p.addedDate desc")
    List<ProductEntity> newArrivals(Pageable pageable);

    List<ProductEntity> findAllByBrandAndActive(String brand,boolean active);

    List<ProductEntity> findBySubCategoryEntityAndActive(SubCategoryEntity subCategoryEntity,boolean active,Pageable pageable);

    @Query("select p from ProductEntity p WHERE p.brand = :brand AND p.productCode >= :minPrice AND p.addedDate <= :maxPrice AND p.addedTime =:color")
    List<ProductEntity> filterProducts(String brand,double minPrice,double maxPrice,String color);

    @Query(value="SELECT * FROM product WHERE MATCH(name,description,brand,color) AGAINST(?1)",nativeQuery = true)
    List<ProductEntity> searchProducts(String keyword);

    List<ProductEntity> findByDealEntity(DealEntity dealEntity,Pageable pageable);

    Optional<ProductEntity> findByUuid(String uuid);

}
