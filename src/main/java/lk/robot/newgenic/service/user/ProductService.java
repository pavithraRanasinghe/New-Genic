package lk.robot.newgenic.service.user;

import lk.robot.newgenic.dto.Request.FilterDTO;
import lk.robot.newgenic.entity.ProductEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.ws.Response;
import java.util.List;

public interface ProductService {

    ResponseEntity<?> newArrivals(int index,int size);

    ResponseEntity<?> fantechProduct();

    ResponseEntity<?> filterProducts(FilterDTO filterDTO);

    ResponseEntity<?> getDetail(long productId);

    ResponseEntity<?> relatedProduct(long productId,int index,int size);

    ResponseEntity<?> searchProduct(String keyword,int index,int size);

    ResponseEntity<?> getSaleProducts(int index,int size);
}
