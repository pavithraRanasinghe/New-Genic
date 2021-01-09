package lk.robot.newgenic.controller;

import lk.robot.newgenic.dto.Request.FilterDTO;
import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/newArrivals",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(){
        return productService.newArrivals();
    }

    @GetMapping(value = "/fantech",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fantechProductcs(){
        return productService.fantechProduct();
    }

    @GetMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> filterproduct(@RequestBody FilterDTO filterDTO){
        // TODO: 31/12/2020 Not completed filter products
        return productService.filterProducts(filterDTO);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getDetail(@PathVariable long productId){
        return productService.getDetail(productId);
    }

}
