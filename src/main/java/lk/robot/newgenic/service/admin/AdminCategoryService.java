package lk.robot.newgenic.service.admin;

import lk.robot.newgenic.dto.admin.request.CategoryRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AdminCategoryService {

    ResponseEntity<?> addCategory(CategoryRequestDTO categoryRequestDTO,long adminId);
}
