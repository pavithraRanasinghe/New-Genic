package lk.robot.newgenic.controller.user;

import lk.robot.newgenic.service.user.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
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

    @GetMapping("/mainSubCategoryProducts/{mainSubCategoryId}")
    public ResponseEntity<?> getMainSubCategoryProducts(@PathVariable long mainSubCategoryId,@RequestParam int index,@RequestParam int size){
        return categoryService.getMainSubCategoryProducts(mainSubCategoryId,index,size);
    }

    @GetMapping("/subCategoryProducts/{subCategoryId}")
    public ResponseEntity<?> getSubCategoryProducts(@PathVariable long subCategoryId,@RequestParam int index,@RequestParam int size){
        return categoryService.getSubCategoryProducts(subCategoryId,index,size);
    }
}
