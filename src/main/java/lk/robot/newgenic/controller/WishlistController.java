package lk.robot.newgenic.controller;

import lk.robot.newgenic.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> addToWishList(){
        // TODO: 31/12/2020 Use Token
        return null;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getWishList(@PathVariable long userId){
        return wishlistService.getWishList(userId);
    }

}
