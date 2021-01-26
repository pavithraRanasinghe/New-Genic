package lk.robot.newgenic.controller;

import lk.robot.newgenic.dto.user.request.FilterDTO;
import lk.robot.newgenic.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    private ProductService productService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/newArrivals", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNewArrival(@RequestParam int index,
                                           @RequestParam int size) {
        LOGGER.info("request - allUser | newArrivals | index: {} | size: {}", index, size);
        ResponseEntity<?> newArrivals = productService.newArrivals(index, size);
        LOGGER.info("response - allUser | newArrivals | newArrivalResponse", newArrivals.getStatusCode());
        return newArrivals;
    }

    @GetMapping(value = "/fantech", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fantechProductcs() {
        LOGGER.info("request - allUser | fantech");
        ResponseEntity<?> fantechProduct = productService.fantechProduct();
        LOGGER.info("response - allUser | fantech");
        return fantechProduct;

    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> filterproduct(@RequestBody FilterDTO filterDTO) {
        // TODO: 31/12/2020 Not completed filter products
        return productService.filterProducts(filterDTO);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getDetail(@PathVariable long productId) {
        LOGGER.info("request - allUser | getProductDetail | productId: {}", productId);
        ResponseEntity<?> detail = productService.getDetail(productId);
        LOGGER.info("response - allUser | getProductDetail | productDetail: {}", detail.getBody().toString());
        return detail;
    }

    @GetMapping("/relatedProducts/{productId}")
    public ResponseEntity<?> getRelatedProducts(@PathVariable long productId,
                                                @RequestParam int index,
                                                @RequestParam int size) {
        LOGGER.info("request - allUser | getRelatedProduct | productId: {} | index: {} | size: {}", productId, index, size);
        ResponseEntity<?> relatedProduct = productService.relatedProduct(productId, index, size);
        LOGGER.info("response - allUser | getProductDetail | relatedProduct: {}",relatedProduct.getStatusCode());
        return relatedProduct;
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@PathVariable String keyword,
                                    @RequestParam int index,
                                    @RequestParam int size) {
        LOGGER.info("request - allUser | searchProducts | keyword: {} | index: {} | size: {}",keyword,index,size);
        ResponseEntity<?> searchProduct = productService.searchProduct(keyword, index, size);
        LOGGER.info("response - allUser | searchProducts | searchResult: {}",searchProduct.getStatusCode());
        return searchProduct;
    }

    @GetMapping("/sale")
    public ResponseEntity<?> getSaleProducts(@RequestParam int index,
                                             @RequestParam int size){
        LOGGER.info("request - allUsers | getSaleProducts | index: {} | size: {}",index,size);
        ResponseEntity<?> saleProducts = productService.getSaleProducts(index, size);
        LOGGER.info("response - allUsers | getSaleProducts | saleProducts: {}",saleProducts.getBody().toString());
        return saleProducts;
    }
}
