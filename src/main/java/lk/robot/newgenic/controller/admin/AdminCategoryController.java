package lk.robot.newgenic.controller.admin;

import lk.robot.newgenic.dto.admin.request.CategoryRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminCategoryController.class);

    @GetMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        return null;
    }
}
