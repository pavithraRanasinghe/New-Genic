package lk.robot.newgenic.service.user;

import org.springframework.http.ResponseEntity;

public interface CategoryService {

    ResponseEntity<?> getAll();

    ResponseEntity<?> getMainSubCategoryProducts(long mainSubCategoryId,int index,int size);

    ResponseEntity<?> getSubCategoryProducts(long subCategoryId,int index,int size);
}
