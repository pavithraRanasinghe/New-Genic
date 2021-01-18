package lk.robot.newgenic.service.user;

import lk.robot.newgenic.dto.Request.CartRequestDTO;
import org.springframework.http.ResponseEntity;

public interface CartService {

    ResponseEntity<?> addToCart(CartRequestDTO cartRequestDTO, long userId);

    ResponseEntity<?> getCart(long userId);

    ResponseEntity<?> cartOrder(long userId);
}
