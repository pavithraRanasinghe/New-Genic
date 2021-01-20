package lk.robot.newgenic.controller.admin;

import lk.robot.newgenic.dto.admin.request.CategoryRequestDTO;
import lk.robot.newgenic.service.admin.AdminCategoryService;
import lk.robot.newgenic.service.user.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminCategoryController.class);
    private AdminCategoryService categoryService;
    private CategoryService userCategoryService;

    @Autowired
    public AdminCategoryController(AdminCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO, Principal principal){
        LOGGER.info("request - admin | addCategory | categoryRequest: {} | adminId: {}",categoryRequestDTO,principal.getName());
        long adminId = Long.parseLong(principal.getName());
        ResponseEntity<?> category = categoryService.addCategory(categoryRequestDTO, adminId);
        LOGGER.info("response - admin | addCategory | category: {}",category.getBody());
        return category;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCategories(Principal principal){
        LOGGER.info("request - admin | getCategory | adminId: {}",principal.getName());
        ResponseEntity<?> all = userCategoryService.getAll();
        LOGGER.info("response - admin | getCategory | categories: {}",all.getStatusCode());
        return all;
    }
}
