package lk.robot.newgenic.controller.user;

import lk.robot.newgenic.service.user.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    private CategoryService categoryService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        LOGGER.info("request  - allUser | getAllCategories ");
        ResponseEntity<?> categories = categoryService.getAll();
        LOGGER.info("response - allUser | getAllCategories | categories: {}",categories.getStatusCode());
        return categories;
    }

    @GetMapping("/mainSubCategoryProducts/{mainSubCategoryId}")
    public ResponseEntity<?> getMainSubCategoryProducts(@PathVariable long mainSubCategoryId,
                                                        @RequestParam int index,
                                                        @RequestParam int size){
        LOGGER.info("request  - allUser | getMainSubCategoryProducts | mainSubCategoryId: {} | index: {} | size: {}",mainSubCategoryId,index,size);
        ResponseEntity<?> mainSubCategoryProducts = categoryService.getMainSubCategoryProducts(mainSubCategoryId, index, size);
        LOGGER.info("response - allUser | getMainSubCategoryProducts | mainSubCategoryProducts{}",mainSubCategoryProducts.getStatusCode());
        return mainSubCategoryProducts;
    }

    @GetMapping("/subCategoryProducts/{subCategoryId}")
    public ResponseEntity<?> getSubCategoryProducts(@PathVariable long subCategoryId,
                                                    @RequestParam int index,
                                                    @RequestParam int size){
        LOGGER.info("request  - allUser | getSubCategoryProducts | subCategoryId: {} | index: {} | size: {}",subCategoryId,index,size);
        ResponseEntity<?> subCategoryProducts = categoryService.getSubCategoryProducts(subCategoryId, index, size);
        LOGGER.info("response - allUser | getSubCategoryProducts | subCategoryProducts{}",subCategoryProducts.getStatusCode());
        return subCategoryProducts;
    }
}
