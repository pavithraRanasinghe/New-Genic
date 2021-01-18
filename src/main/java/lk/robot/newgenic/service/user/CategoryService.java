package lk.robot.newgenic.service.user;

import lk.robot.newgenic.dto.response.CategoryResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {

    ResponseEntity<?> getAll();

    ResponseEntity<?> getMainSubCategoryProducts(long mainSubCategoryId,int index,int size);

    ResponseEntity<?> getSubCategoryProducts(long subCategoryId,int index,int size);
}
