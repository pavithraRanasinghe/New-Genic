package lk.robot.newgenic.controller.admin;

import lk.robot.newgenic.dto.admin.request.CategoryRequestDTO;
import lk.robot.newgenic.service.admin.AdminCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminCategoryController.class);
    private AdminCategoryService categoryService;

    @Autowired
    public AdminCategoryController(AdminCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO, Principal principal){
        LOGGER.info("request - admin | addCategory | categoryRequest: {} | adminId: {}",categoryRequestDTO,principal.getName());
        long adminId = Long.parseLong(principal.getName());
        ResponseEntity<?> category = categoryService.addCategory(categoryRequestDTO, adminId);
        LOGGER.info("response - admin | addCategory | category: {}",category.getBody());
        return category;
    }
}
