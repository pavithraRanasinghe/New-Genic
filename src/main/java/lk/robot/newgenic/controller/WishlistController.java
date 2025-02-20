package lk.robot.newgenic.controller;

import lk.robot.newgenic.service.WishlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WishlistController {

    private WishlistService wishlistService;
    private static final Logger LOGGER = LoggerFactory.getLogger(WishlistController.class);

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping
    public ResponseEntity<?> addToWishList(@PathVariable String productId,
                                           Principal principal){
        LOGGER.info("request - registeredUser | addToWishList | productId: {} | userId: {}",productId,principal.getName());
        String userId = principal.getName();
        ResponseEntity<?> toWishlist = wishlistService.addToWishlist(productId, userId);
        LOGGER.info("response - registeredUser | addToWishList | wishlist: {}",toWishlist.getBody().toString());
        return toWishlist;
    }

    @GetMapping()
    public ResponseEntity<?> getWishList(Principal principal){
        LOGGER.info("request - registeredUser | getWishList | userId: {}",principal.getName());
        String userId = principal.getName();
        ResponseEntity<?> wishList = wishlistService.getWishList(userId);
        LOGGER.info("response - registeredUser | getWishList | wishlist: {}",wishList.getBody().toString());
        return wishList;
    }

    @GetMapping("/test")
    public ResponseEntity<String> get(){
        return new ResponseEntity<String>("Back end", HttpStatus.OK);
    }

}
