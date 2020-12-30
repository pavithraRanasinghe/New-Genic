package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.ProductDTO;
import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.ProductRepository;
import lk.robot.newgenic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    @Override
    public ResponseEntity<?> newArrivals() {

        try {
            List<ProductEntity> list = productRepository.newArrivals(PageRequest.of(0,14));
            if (!list.isEmpty()){
                List<ProductDTO> newArrivalList = new ArrayList<>();
                for (ProductEntity productEntity:list) {
                    ProductDTO productDTO = entityToDto(productEntity);
                    newArrivalList.add(productDTO);
                }
                return new ResponseEntity<>(newArrivalList,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("New arrivals not found", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            throw new CustomException("Something went wrong in new arrivals");
        }

    }

    @Override
    public ResponseEntity<?> fantechProduct() {
        try{
            List<ProductEntity> fantech = productRepository.findAllByBrand("Fantech");
            if (!fantech.isEmpty()){
                List<ProductDTO> fantechList = new ArrayList<>();
                for (ProductEntity productEntity:fantech) {
                    ProductDTO productDTO = entityToDto(productEntity);
                    fantechList.add(productDTO);
                }
                return new ResponseEntity<>(fantechList,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Fantech products not found", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            throw new CustomException("Something went wrong in fantech products");
        }
    }

    private ProductDTO entityToDto(ProductEntity productEntity){
        return new ProductDTO(
                productEntity.getProductId(),
                productEntity.getProductCode(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getStock(),
                productEntity.getColor(),
                productEntity.getSize(),
                productEntity.getGender(),
                productEntity.getBuyingPrice(),
                productEntity.getSalePrice(),
                productEntity.getRetailPrice(),
                productEntity.getAddedDate(),
                productEntity.isActive()
        );
    }
}
