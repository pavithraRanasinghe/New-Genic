package lk.robot.newgenic.service.impl;

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
            List<ProductEntity> newArrivalList = productRepository.newArrivals(PageRequest.of(0,14));
            if (!newArrivalList.isEmpty()){
                return new ResponseEntity<>(newArrivalList,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("New arrivals not found", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            throw new CustomException("Something went wrong in new arrivals");
        }

    }
}
