package lk.robot.newgenic.service.user;

import lk.robot.newgenic.dto.user.Request.CartOrderRequestDTO;
import lk.robot.newgenic.dto.user.Request.OrderRequestDTO;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<?> placeOrder(OrderRequestDTO orderRequestDTO,long userId);

    ResponseEntity<?> cartOrderPlace(CartOrderRequestDTO cartOrderRequestDTO,long userId);

    ResponseEntity<?> getOrders(long userId);
}
