package lk.robot.newgenic.controller;

import lk.robot.newgenic.dto.Request.CartRequestDTO;
import lk.robot.newgenic.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<?> addToCart(@RequestBody CartRequestDTO cartRequestDTO, Principal principal){
        if (principal == null){
            return new ResponseEntity<>("Unauthorized to access", HttpStatus.UNAUTHORIZED);
        }

        long userId = Long.parseLong(principal.getName());
        return cartService.addToCart(cartRequestDTO,userId);
    }

    @GetMapping
    public ResponseEntity<?> getCart(Principal principal){
        long userId = Long.parseLong(principal.getName());
        return cartService.getCart(userId);
    }

    @GetMapping("/cartOrderDetail")
    public ResponseEntity<?> cartOrderDetail(Principal principal){
        long userId = Long.parseLong(principal.getName());
        return cartService.cartOrder(userId);
    }
}
