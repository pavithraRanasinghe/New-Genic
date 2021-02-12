package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.request.CartOrderRequestDTO;
import lk.robot.newgenic.dto.request.OrderRequestDTO;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<?> placeOrder(OrderRequestDTO orderRequestDTO,long userId);

    ResponseEntity<?> cartOrderPlace(CartOrderRequestDTO cartOrderRequestDTO,long userId);

    ResponseEntity<?> getOrders(long userId);
}
