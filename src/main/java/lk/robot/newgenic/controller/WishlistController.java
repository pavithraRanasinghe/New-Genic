package lk.robot.newgenic.controller;

import lk.robot.newgenic.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/wishlist")
@CrossOrigin
public class WishlistController {

    private WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping
    public ResponseEntity<?> addToWishList(@PathVariable long productId, Principal principal){
        long userId = Long.parseLong(String.valueOf(principal));
        return wishlistService.addToWishlist(productId,userId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getWishList(@PathVariable long userId){
        return wishlistService.getWishList(userId);
    }

}
