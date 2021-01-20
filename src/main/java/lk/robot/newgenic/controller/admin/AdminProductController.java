package lk.robot.newgenic.controller.admin;

import lk.robot.newgenic.dto.admin.request.ProductRequestDTO;
import lk.robot.newgenic.service.admin.AdminProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class AdminProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminProductController.class);
    private AdminProductService adminProductService;

    @Autowired
    public AdminProductController(AdminProductService adminProductService) {
        this.adminProductService = adminProductService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody List<ProductRequestDTO> productRequestList, Principal principal){
        // TODO: 20/01/2021 Didn't add image!!!!!! Should add it
        LOGGER.info("request - admin | addProduct | productList:{} | adminId: {}",productRequestList,principal.getName());
        ResponseEntity<?> addProduct = adminProductService.addProduct(productRequestList);
        LOGGER.info("response - admin | addProduct | product: {}",addProduct.getBody().toString());
        return addProduct;
    }
}
