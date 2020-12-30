package lk.robot.newgenic.controller;

import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/newArrivals")
    public ResponseEntity<?> getAll(){
        return productService.newArrivals();
    }
}
