package lk.robot.newgenic.service;

import org.springframework.http.ResponseEntity;

public interface WishlistService {

    ResponseEntity<?> addToWishlist(long productId,long userId);

    ResponseEntity<?> getWishList(long userId);
}
