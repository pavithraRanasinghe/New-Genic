package lk.robot.newgenic.controller;

import lk.robot.newgenic.dto.CartUpdateDetailRequestDTO;
import lk.robot.newgenic.dto.request.CartRequestDTO;
import lk.robot.newgenic.dto.request.CartUpdateRequestDTO;
import lk.robot.newgenic.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    private CartService cartService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<?> addToCart(@RequestBody CartRequestDTO cartRequestDTO, Principal principal){
        LOGGER.info("request  - registeredUser | addToCart | userId : {} | cartRequestDTO: {}", principal.getName(),cartRequestDTO);
        String userId = principal.getName();
        ResponseEntity<?> cart = cartService.addToCart(cartRequestDTO, userId);
        LOGGER.info("response - registeredUser | addToCart | cart: {}",cart.getBody().toString());
        return cart;
    }

    @GetMapping
    public ResponseEntity<?> getCart(Principal principal){
        LOGGER.info("request  - registeredUser | getCart | userId : {}", principal.getName());
        String userId = principal.getName();
        ResponseEntity<?> cart = cartService.getCart(userId);
        LOGGER.info("response - registeredUser | getCart | cart: {}",cart.getStatusCode());
        return cart;
    }

    @GetMapping("/cartOrderDetail")
    public ResponseEntity<?> cartOrderDetail(Principal principal){
        LOGGER.info("request  - registeredUser | getCaryOrderDetails | userId : {}", principal.getName());
        ResponseEntity<?> responseEntity = cartService.cartOrder(principal.getName());
        LOGGER.info("response - registeredUser | getCaryOrderDetails | cartDetails: {}",responseEntity.getStatusCode());
        return responseEntity;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCart(@RequestBody CartUpdateRequestDTO detailRequestDTO, Principal principal){
        LOGGER.info("request - registeredUser | updateCart | cartUpdateDetailRequest: {} | userId: {}",detailRequestDTO,principal.getName());
        ResponseEntity<?> responseEntity = cartService.updateCart(detailRequestDTO,principal.getName());
        LOGGER.info("response - registeredUser | updateCart | response; {}",responseEntity.getBody());
        return responseEntity;
    }
}
