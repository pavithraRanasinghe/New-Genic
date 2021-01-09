package lk.robot.newgenic.controller;

import lk.robot.newgenic.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin
public class CategoryController {


    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/mainSubCategoryProducts/{mainSubCategoryid}")
    public ResponseEntity<?> getMainSubCategoryProducts(@PathVariable long mainSubCategoryid){
        return categoryService.getMainSubCategoryProducts(mainSubCategoryid);
    }

    @GetMapping("/subCategoryProducts/{subCategoryid}")
    public ResponseEntity<?> getSubCategoryProducts(@PathVariable long subCategoryid){
        return categoryService.getSubCategoryProducts(subCategoryid);
    }
}
