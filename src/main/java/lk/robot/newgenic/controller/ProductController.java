package lk.robot.newgenic.controller;

import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public String save(){
        return "";
    }

    @GetMapping("/getAll")
    public List<ProductEntity> getAll(){
        return productService.getAll();
    }
}
