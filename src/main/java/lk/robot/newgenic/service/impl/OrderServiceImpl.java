package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.Request.OrderRequestDTO;
import lk.robot.newgenic.repository.OrderDetailRepository;
import lk.robot.newgenic.repository.OrderRepository;
import lk.robot.newgenic.repository.UserRepository;
import lk.robot.newgenic.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;

    @Override
    public ResponseEntity<?> placeOrder(OrderRequestDTO orderRequestDTO) {
        return null;
    }
}
