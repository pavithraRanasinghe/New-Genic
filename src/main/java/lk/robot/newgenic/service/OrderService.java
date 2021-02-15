package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.request.CartOrderRequestDTO;
import lk.robot.newgenic.dto.request.OrderRequestDTO;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<?> placeOrder(OrderRequestDTO orderRequestDTO,String userId);

    ResponseEntity<?> cartOrderPlace(CartOrderRequestDTO cartOrderRequestDTO,String userId);

    ResponseEntity<?> getOrders(String userId);
}
