package lk.robot.newgenic.service.user.impl;

import lk.robot.newgenic.dto.ProductDTO;
import lk.robot.newgenic.dto.user.Request.FilterDTO;
import lk.robot.newgenic.entity.DealEntity;
import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.enums.DealStatus;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.user.DealRepository;
import lk.robot.newgenic.repository.user.ProductRepository;
import lk.robot.newgenic.service.user.ProductService;
import lk.robot.newgenic.util.EntityToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private DealRepository dealRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              DealRepository dealRepository) {
        this.productRepository = productRepository;
        this.dealRepository = dealRepository;
    }

    @Override
    public ResponseEntity<?> newArrivals(int index, int size) {

        try {
            List<ProductEntity> list = productRepository.newArrivals(PageRequest.of(index, size));
            if (!list.isEmpty()) {
                List<ProductDTO> newArrivalList = new ArrayList<>();
                for (ProductEntity productEntity : list) {
                    ProductDTO productDTO = EntityToDto.productEntityToDto(productEntity);
                    newArrivalList.add(productDTO);
                }
                return new ResponseEntity<>(newArrivalList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("New arrivals not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException("Something went wrong in new arrivals");
        }

    }

    @Override
    public ResponseEntity<?> fantechProduct() {
        try {
            List<ProductEntity> fantech = productRepository.findAllByBrandAndActive("Fantech", true);
            if (!fantech.isEmpty()) {
                List<ProductDTO> fantechList = new ArrayList<>();
                for (ProductEntity productEntity : fantech) {
                    ProductDTO productDTO = EntityToDto.productEntityToDto(productEntity);
                    fantechList.add(productDTO);
                }
                return new ResponseEntity<>(fantechList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Fantech products not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException("Something went wrong in fantech products");
        }
    }

    @Override
    public ResponseEntity<?> filterProducts(FilterDTO filterDTO) {
        try {
            if (filterDTO != null) {
                productRepository.filterProducts(
                        filterDTO.getBrand(),
                        filterDTO.getMinPrice(),
                        filterDTO.getMaxPrice(),
                        filterDTO.getColor());
            } else {
                return new ResponseEntity<>("Filter options required", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException("Product Filter failed");
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getDetail(long productId) {
        if (productId != 0) {
            Optional<ProductEntity> productEntity = productRepository.findById(productId);
            if (productEntity.isPresent()) {
                ProductDTO productDTO = EntityToDto.productEntityToDto(productEntity.get());
                return new ResponseEntity<>(productDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Product ID not found", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> relatedProduct(long productId, int index, int size) {
        try {
            if (productId != 0) {
                Optional<ProductEntity> productEntity = productRepository.findById(productId);
                if (productEntity.isPresent()) {
                    List<ProductEntity> productEntityList = productRepository.
                            findBySubCategoryEntityAndActive(productEntity.get().getSubCategoryEntity(), true, PageRequest.of(index, size));
                    if (!productEntityList.isEmpty()) {
                        List<ProductDTO> productList = new ArrayList<>();
                        for (ProductEntity product : productEntityList) {
                            productList.add(EntityToDto.productEntityToDto(product));
                        }
                        return new ResponseEntity<>(productList, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Related products not found", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Product id not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException("Failed to fetch related products");
        }
    }

    @Override
    public ResponseEntity<?> searchProduct(String keyword, int index, int size) {
        try {
            if (keyword != null) {
                return null;
            } else {
                return new ResponseEntity<>("Keyword not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException("Search failed");
        }
    }

    @Override
    public ResponseEntity<?> getSaleProducts(int index, int size) {
        try{
            DealEntity dealEntity = dealRepository.findByDealStatus(DealStatus.ACTIVE.toString());
            if (dealEntity != null){
                List<ProductEntity> products = productRepository.findByDealEntity(dealEntity,PageRequest.of(index,size));
                if (!products.isEmpty()){
                    List<ProductDTO> productList = new ArrayList<>();
                    for (ProductEntity productEntity :
                            products) {
                        productList.add(EntityToDto.productEntityToDto(productEntity));
                    }
                    return new ResponseEntity<>(productList,HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("No products found",HttpStatus.NOT_FOUND);
                }
            }else {
                return new ResponseEntity<>("Deals not found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            throw new CustomException("Failed to get sale products");
        }
    }
}
