package lk.robot.newgenic.service.admin;

import lk.robot.newgenic.dto.admin.request.ProductRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminProductService {

    ResponseEntity<?> addProduct(List<ProductRequestDTO> productRequestList);
}
