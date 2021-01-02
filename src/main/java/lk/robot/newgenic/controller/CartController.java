package lk.robot.newgenic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    @PostMapping("/{productId}")
    public ResponseEntity<?> addToCart(@PathVariable long productId){
        return null;
    }
}
