package lk.robot.newgenic.service;

import org.springframework.http.ResponseEntity;

public interface WishlistService {

    ResponseEntity<?> addToWishlist();

    ResponseEntity<?> getWishList(long userId);
}
