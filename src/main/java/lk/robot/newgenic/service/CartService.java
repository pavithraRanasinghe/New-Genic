package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.request.CartRequestDTO;
import lk.robot.newgenic.dto.request.CartUpdateRequestDTO;
import org.springframework.http.ResponseEntity;

public interface CartService {

    ResponseEntity<?> addToCart(CartRequestDTO cartRequestDTO, String userId);

    ResponseEntity<?> getCart(String userId);

    ResponseEntity<?> cartOrder(String userId);

    ResponseEntity<?> updateCart(CartUpdateRequestDTO cartUpdateRequestDTO,String userId);
}
