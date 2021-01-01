package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.ProductDTO;
import lk.robot.newgenic.entity.UserEntity;
import lk.robot.newgenic.entity.WishlistEntity;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.ProductRepository;
import lk.robot.newgenic.repository.UserRepository;
import lk.robot.newgenic.repository.WishlistRepository;
import lk.robot.newgenic.service.WishlistService;
import lk.robot.newgenic.util.EntityToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    private WishlistRepository wishlistRepository;
    private UserRepository userRepository;

    @Autowired
    public WishlistServiceImpl(
            WishlistRepository wishlistRepository,
            UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> addToWishlist() {
        return null;
    }

    @Override
    public ResponseEntity<?> getWishList(long userId) {
        try {
            if (userId != 0) {
                Optional<UserEntity> userEntity = userRepository.findById(userId);
                if (userEntity.isPresent()) {
                    List<WishlistEntity> wishList = wishlistRepository.findByUserEntity(userEntity.get());
                    List<ProductDTO> productWishList = new ArrayList<>();
                    if(!wishList.isEmpty()){
                        for (WishlistEntity wishlistEntity:wishList) {
                            ProductDTO productDTO = EntityToDto.productEntityToDto(wishlistEntity.getProductEntity());
                            productWishList.add(productDTO);
                        }
                        return new ResponseEntity<>(productWishList,HttpStatus.OK);
                    }else {
                        return new ResponseEntity<>("Wishlist not found",HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("User ID not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException("Wishlist Fetching failed");
        }
    }
}
