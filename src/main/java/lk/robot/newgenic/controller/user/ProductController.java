package lk.robot.newgenic.controller.user;

import lk.robot.newgenic.dto.Request.FilterDTO;
import lk.robot.newgenic.service.user.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/newArrivals", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNewArrival(@RequestParam int index, @RequestParam int size) {
        return productService.newArrivals(index, size);
    }

    @GetMapping(value = "/fantech", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fantechProductcs() {
        return productService.fantechProduct();
    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> filterproduct(@RequestBody FilterDTO filterDTO) {
        // TODO: 31/12/2020 Not completed filter products
        return productService.filterProducts(filterDTO);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getDetail(@PathVariable long productId) {
        return productService.getDetail(productId);
    }

    @GetMapping("/relatedProducts/{productId}")
    public ResponseEntity<?> getRelatedProducts(@PathVariable long productId, @RequestParam int index, @RequestParam int size) {
        return productService.relatedProduct(productId, index, size);
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@PathVariable String keyword,@RequestParam int index,@RequestParam int size){
        return productService.searchProduct(keyword, index, size);
    }
}
