package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.Request.CartRequestDTO;
import org.springframework.http.ResponseEntity;

public interface CartService {

    ResponseEntity<?> addToCart(CartRequestDTO cartRequestDTO, long userId);
}
