package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.entity.OrderDetailEntity;
import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.OrderDetailRepository;
import lk.robot.newgenic.repository.OrderRepository;
import lk.robot.newgenic.repository.ProductRepository;
import lk.robot.newgenic.service.CartService;
import lk.robot.newgenic.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public CartServiceImpl(
            ProductRepository productRepository,
            OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public ResponseEntity<?> addToCart(long productId) {
        try{
            if (productId != 0){
                Optional<ProductEntity> productEntity = productRepository.findById(productId);
                if (productEntity.isPresent()){

                    // TODO: 01/01/2021 Use token for fetch user

                    OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
                    orderDetailEntity.setOrderDate(DateConverter.localDateToUtil(LocalDate.now()));
                    orderDetailEntity.setOrderTime(DateConverter.localTimeToUtil(LocalTime.now()));
                    orderDetailEntity.setProductEntity(productEntity.get());

                }else {
                    return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
                }
            }else{
                return new ResponseEntity<>("Product ID not found", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new CustomException("Product add to cart failed");
        }
        return null;
    }
}
