package lk.robot.newgenic.service;

import org.springframework.http.ResponseEntity;

public interface WishlistService {

    ResponseEntity<?> addToWishlist(String productId,String userId);

    ResponseEntity<?> getWishList(String userId);
}
