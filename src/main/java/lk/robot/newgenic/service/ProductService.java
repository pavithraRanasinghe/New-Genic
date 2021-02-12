package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.request.FilterDTO;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<?> newArrivals(int index,int size);

    ResponseEntity<?> fantechProduct();

    ResponseEntity<?> filterProducts(FilterDTO filterDTO);

    ResponseEntity<?> getDetail(String productId);

    ResponseEntity<?> relatedProduct(String productId,int index,int size);

    ResponseEntity<?> searchProduct(String keyword,int index,int size);

    ResponseEntity<?> getSaleProducts(int index,int size);
}
