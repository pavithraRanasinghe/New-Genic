package lk.robot.newgenic.controller.user;

import lk.robot.newgenic.dto.Request.CartOrderRequestDTO;
import lk.robot.newgenic.dto.Request.OrderRequestDTO;
import lk.robot.newgenic.service.user.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDTO orderRequestDTO, Principal principal){
        long userId = Long.parseLong(principal.getName());
        return orderService.placeOrder(orderRequestDTO,userId);
    }

    @PostMapping("/cartOrder")
    public ResponseEntity<?> cartOrder(@RequestBody CartOrderRequestDTO cartOrderRequestDTO,Principal principal){
        long userId = Long.parseLong(principal.getName());
        return orderService.cartOrderPlace(cartOrderRequestDTO,userId);
    }

    @GetMapping()
    public ResponseEntity<?> getOrders(Principal principal){
        long userId = Long.parseLong(principal.getName());
        return orderService.getOrders(userId);
    }
}
