package lk.robot.newgenic.controller;

import lk.robot.newgenic.dto.request.CartOrderRequestDTO;
import lk.robot.newgenic.dto.request.OrderRequestDTO;
import lk.robot.newgenic.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    private OrderService orderService;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOneProductOrder(@RequestBody OrderRequestDTO orderRequestDTO,
                                        Principal principal){
        LOGGER.info("request - registeredUser | placeOneProductOrder | orderRequest: {} | userId: {}",orderRequestDTO,principal.getName());
        ResponseEntity<?> placeOrderResponse = orderService.placeOrder(orderRequestDTO, principal.getName());
        LOGGER.info("response - registeredUser | placeOneProductOrder | placeOneProductOrderResponse: {}",placeOrderResponse.getStatusCode());
        return placeOrderResponse;
    }

    @PostMapping("/cartOrder")
    public ResponseEntity<?> cartOrder(@RequestBody CartOrderRequestDTO cartOrderRequestDTO,
                                       Principal principal){
        LOGGER.info("request - registeredUser | placeCartOrder | cartOrderRequest: {} | userId: {}",cartOrderRequestDTO,principal.getName());
        ResponseEntity<?> placeOrderResponse = orderService.cartOrderPlace(cartOrderRequestDTO, principal.getName());
        LOGGER.info("response - registeredUser | placeCartOrder | placeCartOrderResponse: {} ",placeOrderResponse.getStatusCode());
        return placeOrderResponse;
    }

    @GetMapping()
    public ResponseEntity<?> getOrders(Principal principal){
        LOGGER.info("request - registeredUser | getOrders | userId: {}",principal.getName());
        ResponseEntity<?> orders = orderService.getOrders(principal.getName());
        LOGGER.info("response - registeredUser | getOrders | getOrderResponse: {}",orders.getStatusCode());
        return orders;
    }
}
