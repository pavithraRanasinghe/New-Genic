package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.Request.OrderRequestDTO;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<?> placeOrder(OrderRequestDTO orderRequestDTO);
}
