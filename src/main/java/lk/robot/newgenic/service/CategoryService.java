package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.response.CategoryResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {

    ResponseEntity<?> getAll();

    ResponseEntity<?> getMainSubCategoryProducts(long mainSubCategoryId);
}
