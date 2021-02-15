package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.CombinationDTO;
import lk.robot.newgenic.dto.ProductDTO;
import lk.robot.newgenic.dto.VariationDTO;
import lk.robot.newgenic.dto.response.ProductResponseDTO;
import lk.robot.newgenic.entity.*;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.*;
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
    private ProductRepository productRepository;
    private VariationRepository variationRepository;
    private VariationDetailRepository variationDetailRepository;
    private VariationCombinationDetailRepository variationCombinationDetailRepository;

    @Autowired
    public WishlistServiceImpl(
            WishlistRepository wishlistRepository,
            UserRepository userRepository,
            ProductRepository productRepository,
            VariationRepository variationRepository,
            VariationDetailRepository variationDetailRepository,
            VariationCombinationDetailRepository variationCombinationDetailRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.variationRepository = variationRepository;
        this.variationDetailRepository = variationDetailRepository;
        this.variationCombinationDetailRepository = variationCombinationDetailRepository;
    }

    @Override
    public ResponseEntity<?> addToWishlist(String productId, String userId) {
        try {
            if (productId != null && userId != null) {
                Optional<ProductEntity> productEntity = productRepository.findByUuid(productId);
                Optional<UserEntity> userEntity = userRepository.findByUserUuid(userId);

                if (userEntity.isPresent() && productEntity.isPresent()) {
                    WishlistEntity wishlistEntity = new WishlistEntity();
                    wishlistEntity.setProductEntity(productEntity.get());
                    wishlistEntity.setUserEntity(userEntity.get());
                    WishlistEntity save = wishlistRepository.save(wishlistEntity);
                    if (save.equals(null)) {
                        return new ResponseEntity<>("Product not add to wishlist", HttpStatus.BAD_REQUEST);
                    }
                    return new ResponseEntity<>(productEntity.get().getName() + " successfully add to wishlist",
                            HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("user or product not found", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Product id or user id not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException("Product add to wishlist failed");
        }
    }

    @Override
    public ResponseEntity<?> getWishList(String userId) {
        try {
            if (userId != null) {
                Optional<UserEntity> userEntity = userRepository.findByUserUuid(userId);
                if (userEntity.isPresent()) {
                    List<WishlistEntity> wishList = wishlistRepository.findByUserEntity(userEntity.get());
                    List<ProductResponseDTO> productWishList = new ArrayList<>();
                    if (!wishList.isEmpty()) {
                        for (WishlistEntity wishlistEntity : wishList) {
                            ProductResponseDTO productResponseDTO = setProductDetails(wishlistEntity.getProductEntity());
                            productWishList.add(productResponseDTO);
                        }
                        return new ResponseEntity<>(productWishList, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Wishlist not found", HttpStatus.NOT_FOUND);
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

    private ProductResponseDTO setProductDetails(ProductEntity productEntity) {
        List<CombinationDTO> combinationList = new ArrayList<>();
        List<VariationEntity> variationList = variationRepository.findByProductEntity(productEntity);
        for (VariationEntity variationEntity :
                variationList) {
            List<VariationDetailEntity> byVariationEntity = variationDetailRepository.findByVariationEntity(variationEntity);
            for (VariationDetailEntity variationDetailEntity :
                    byVariationEntity) {

                List<VariationCombinationDetailEntity> variationCombinationDetailList = variationCombinationDetailRepository.findByVariationDetailEntity(variationDetailEntity);

                for (VariationCombinationDetailEntity variationCombinationDetailEntity :
                        variationCombinationDetailList) {
                    CombinationEntity combinationEntity = variationCombinationDetailEntity.getCombinationEntity();

                    CombinationDTO combinationDTO = new CombinationDTO();
                    combinationDTO.setCombinationId(combinationEntity.getCombinationId());
                    combinationDTO.setStock(combinationEntity.getStock());
                    combinationDTO.setWeight(combinationEntity.getWeight());
                    combinationDTO.setSalePrice(combinationEntity.getSalePrice());
                    combinationDTO.setRetailPrice(combinationEntity.getRetailPrice());

                    VariationDTO variationDTO = new VariationDTO();
                    VariationDetailEntity variationDetail = variationCombinationDetailEntity.getVariationDetailEntity();
                    variationDTO.setVariationDetailId(variationDetail.getVariationDetailId());
                    variationDTO.setValue(variationDetail.getValue());
                    variationDTO.setVariationId(variationDetail.getVariationEntity().getVariationId());
                    variationDTO.setVariationName(variationDetail.getVariationEntity().getVariationName());

                    if (combinationList.isEmpty()){
                        combinationList.add(combinationDTO);
                    }

                    for (CombinationDTO combination :
                            combinationList) {
                        if (combination.getCombinationId() == combinationEntity.getCombinationId()){
                            combination.getVariationList().add(variationDTO);
                        }else{
                            List<VariationDTO> allVariationList = new ArrayList<>();
                            allVariationList.add(variationDTO);
                            combinationDTO.setVariationList(allVariationList);
                            combinationList.add(combinationDTO);
                        }
                    }
                }
            }
        }
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setUuid(productEntity.getUuid());
        productResponseDTO.setName(productEntity.getName());
        productResponseDTO.setDescription(productEntity.getDescription());
        productResponseDTO.setBrand(productEntity.getBrand());
        productResponseDTO.setFreeShipping(productEntity.isFreeShipping());
        productResponseDTO.setVariationList(combinationList);

        return productResponseDTO;
    }
}
