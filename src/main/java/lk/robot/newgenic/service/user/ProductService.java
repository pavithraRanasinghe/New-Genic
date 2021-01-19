package lk.robot.newgenic.service.user;

import lk.robot.newgenic.dto.user.Request.FilterDTO;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<?> newArrivals(int index,int size);

    ResponseEntity<?> fantechProduct();

    ResponseEntity<?> filterProducts(FilterDTO filterDTO);

    ResponseEntity<?> getDetail(long productId);

    ResponseEntity<?> relatedProduct(long productId,int index,int size);

    ResponseEntity<?> searchProduct(String keyword,int index,int size);

    ResponseEntity<?> getSaleProducts(int index,int size);
}
