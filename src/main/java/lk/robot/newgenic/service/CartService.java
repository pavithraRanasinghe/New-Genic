package lk.robot.newgenic.service;

import org.springframework.http.ResponseEntity;

public interface CartService {

    ResponseEntity<?> addToCart(long productId);
}
