package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.CombinationDTO;
import lk.robot.newgenic.dto.VariationDTO;
import lk.robot.newgenic.dto.request.FilterDTO;
import lk.robot.newgenic.dto.response.ProductResponseDTO;
import lk.robot.newgenic.dto.response.SaleResponseDTO;
import lk.robot.newgenic.entity.*;
import lk.robot.newgenic.enums.DealStatus;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.*;
import lk.robot.newgenic.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private DealRepository dealRepository;
    private VariationDetailRepository variationDetailRepository;
    private VariationRepository variationRepository;
    private VariationCombinationDetailRepository variationCombinationDetailRepository;
    private ProductImageRepository productImageRepository;
    private ModelMapper modelMapper;


    public ProductServiceImpl(ProductRepository productRepository,
                              DealRepository dealRepository,
                              VariationDetailRepository variationDetailRepository,
                              VariationRepository variationRepository,
                              VariationCombinationDetailRepository variationCombinationDetailRepository,
                              ProductImageRepository productImageRepository,
                              ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.dealRepository = dealRepository;
        this.variationDetailRepository = variationDetailRepository;
        this.variationRepository = variationRepository;
        this.variationCombinationDetailRepository = variationCombinationDetailRepository;
        this.productImageRepository = productImageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<?> newArrivals(int index, int size) {

        try {
            List<ProductEntity> list = productRepository.newArrivals(PageRequest.of(index, size));
            if (!list.isEmpty()) {
                List<ProductResponseDTO> newArrivalList = new ArrayList<>();
                for (ProductEntity productEntity : list) {
                    newArrivalList.add(setProductDetails(productEntity));
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
                List<ProductResponseDTO> fantechList = new ArrayList<>();
                for (ProductEntity productEntity : fantech) {
                    fantechList.add(setProductDetails(productEntity));
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
    public ResponseEntity<?> getDetail(String productId) {
        if (productId != null) {
            Optional<ProductEntity> productEntity = productRepository.findByUuid(productId);
            if (productEntity.isPresent()) {
                ProductResponseDTO productResponseDTO = setProductDetails(productEntity.get());
                return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Product ID not found", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> relatedProduct(String productId, int index, int size) {
        try {
            if (productId != null) {
                Optional<ProductEntity> productEntity = productRepository.findByUuid(productId);
                if (productEntity.isPresent()) {
                    List<ProductEntity> productEntityList = productRepository.
                            findBySubCategoryEntityAndActive(productEntity.get().getSubCategoryEntity(), true, PageRequest.of(index, size));
                    if (!productEntityList.isEmpty()) {
                        List<ProductResponseDTO> productList = new ArrayList<>();
                        for (ProductEntity product : productEntityList) {
                            productList.add(setProductDetails(product));
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
                List<ProductEntity> productEntityList = productRepository.searchProducts(keyword);
                if (!productEntityList.isEmpty()) {
                    List<ProductResponseDTO> productList = new ArrayList<>();
                    for (ProductEntity productEntity :
                            productEntityList) {
                        productList.add(setProductDetails(productEntity));
                    }
                    return new ResponseEntity<>(productList, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("No products found", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Keyword not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException("Search failed");
        }
    }

    @Override
    public ResponseEntity<?> getSaleProducts(int index, int size) {
        try {
            DealEntity dealEntity = dealRepository.findByDealStatus(DealStatus.ACTIVE);
            if (dealEntity != null) {
                List<ProductEntity> products = productRepository.findByDealEntity(dealEntity, PageRequest.of(index, size));
                if (!products.isEmpty()) {
                    List<ProductResponseDTO> productList = new ArrayList<>();
                    for (ProductEntity productEntity :
                            products) {
                        productList.add(setProductDetails(productEntity));
                    }
                    SaleResponseDTO saleResponseDTO = new SaleResponseDTO(
                            dealEntity.getDealId(),
                            dealEntity.getName(),
                            dealEntity.getDescription(),
                            dealEntity.getStartDate(),
                            dealEntity.getStartTime(),
                            dealEntity.getEndDate(),
                            dealEntity.getEndTime(),
                            dealEntity.getDiscount(),
                            productList
                    );
                    return new ResponseEntity<>(saleResponseDTO, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("No products found", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Deals not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException("Failed to get sale products");
        }
    }

    private ProductResponseDTO setProductDetails(ProductEntity productEntity) {
        List<CombinationDTO> combinationList = new ArrayList<>();
        List<VariationEntity> variationList = variationRepository.findByProductEntity(productEntity);
        for (VariationEntity variationEntity :
                variationList) {
            for (VariationDetailEntity variationDetailEntity :
                    variationEntity.getVariationDetailEntityList()) {

                List<ProductImageEntity> imageList = productImageRepository.findByVariationDetailEntity(variationDetailEntity);
                List<String> productImageList = new ArrayList<>();
                if (!imageList.isEmpty()){
                    for (ProductImageEntity productImageEntity :
                            imageList) {
                        productImageList.add(productImageEntity.getUrl());
                    }
                }

                for (VariationCombinationDetailEntity variationCombinationDetailEntity :
                        variationDetailEntity.getVariationCombinationList()) {
                    CombinationEntity combinationEntity = variationCombinationDetailEntity.getCombinationEntity();

                    VariationDTO variationDTO = new VariationDTO(
                            variationCombinationDetailEntity.getVariationDetailEntity().getVariationEntity().getVariationId(),
                            variationCombinationDetailEntity.getVariationDetailEntity().getVariationEntity().getVariationName(),
                            variationCombinationDetailEntity.getVariationDetailEntity().getVariationDetailId(),
                            variationCombinationDetailEntity.getVariationDetailEntity().getValue()
                    );

                    if (!productImageList.isEmpty()){
                        variationDTO.setImageList(productImageList);
                    }

                    if (combinationList.isEmpty()) {
                        CombinationDTO combinationDTO = modelMapper.map(combinationEntity, CombinationDTO.class);
                        List<VariationDTO> allVariationList = new ArrayList<>();
                        allVariationList.add(variationDTO);
                        combinationDTO.setVariationList(allVariationList);
                        combinationList.add(combinationDTO);
                    } else {
                        boolean combinationSet = true;
                        for (CombinationDTO combination :
                                combinationList) {
                            if (combination.getCombinationId() == combinationEntity.getCombinationId()) {
                                combination.getVariationList().add(variationDTO);
                                combinationSet = false;
                            }
                        }
                        if (combinationSet) {
                            CombinationDTO combinationDTO = modelMapper.map(combinationEntity, CombinationDTO.class);
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
        productResponseDTO.setProductCode(productEntity.getProductCode());
        productResponseDTO.setName(productEntity.getName());
        productResponseDTO.setDescription(productEntity.getDescription());
        productResponseDTO.setBrand(productEntity.getBrand());
        productResponseDTO.setFreeShipping(productEntity.isFreeShipping());
        productResponseDTO.setCombinationList(combinationList);

        return productResponseDTO;
    }
}
