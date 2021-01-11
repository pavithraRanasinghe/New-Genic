package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.ProductDTO;
import lk.robot.newgenic.dto.Request.CartRequestDTO;
import lk.robot.newgenic.entity.OrderDetailEntity;
import lk.robot.newgenic.entity.OrderEntity;
import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.entity.UserEntity;
import lk.robot.newgenic.enums.OrderStatus;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.OrderDetailRepository;
import lk.robot.newgenic.repository.OrderRepository;
import lk.robot.newgenic.repository.ProductRepository;
import lk.robot.newgenic.repository.UserRepository;
import lk.robot.newgenic.service.CartService;
import lk.robot.newgenic.util.DateConverter;
import lk.robot.newgenic.util.EntityToDto;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public CartServiceImpl(
            ProductRepository productRepository,
            OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository,
            UserRepository userRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> addToCart(CartRequestDTO cartRequestDTO, long userId) {
        try{
            if (cartRequestDTO.getProductId() != 0){
                Optional<ProductEntity> productEntity = productRepository.findById(cartRequestDTO.getProductId());
                Optional<UserEntity> userEntity = userRepository.findById(userId);
                if (productEntity.isPresent() && userEntity.isPresent()){
                    OrderEntity existCart = orderRepository.findByUserEntityAndStatus(userEntity.get(),
                            OrderStatus.CART.toString());
                    if(existCart != null && existCart.getUserEntity().equals(userEntity.get())){
                        OrderDetailEntity existProduct = orderDetailRepository.
                                findByOrderEntityAndProductEntity(existCart, productEntity.get());
                        if (existProduct != null){
                            return new ResponseEntity<>("Product already added to cart",HttpStatus.CONFLICT);
                        }
                        existCart.setOrderPrice(existCart.getOrderPrice() + cartRequestDTO.getOrderPrice());
                        existCart.setTotalWeight(existCart.getTotalWeight() + cartRequestDTO.getWeight());

                        OrderEntity order = orderRepository.save(existCart);
                        if (!order.equals(null)){
                            OrderDetailEntity orderDetailEntity = setValuesToOrderDetail(order, cartRequestDTO, productEntity.get());

                            OrderDetailEntity save = orderDetailRepository.save(orderDetailEntity);
                            return new ResponseEntity<>("Product add to cart successful",HttpStatus.OK);
                        }else {
                            return new ResponseEntity<>("Product add to cart failed",HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    }else{
                        OrderEntity orderEntity = new OrderEntity();
                        orderEntity.setOrderPrice(cartRequestDTO.getOrderPrice());
                        orderEntity.setStatus(OrderStatus.CART.toString());
                        orderEntity.setTotalWeight(cartRequestDTO.getWeight());
                        orderEntity.setUserEntity(userEntity.get());

                        OrderEntity order = orderRepository.save(orderEntity);
                        if (!order.equals(null)){
                            OrderDetailEntity orderDetailEntity = setValuesToOrderDetail(order, cartRequestDTO, productEntity.get());
                            OrderDetailEntity save = orderDetailRepository.save(orderDetailEntity);

                            return new ResponseEntity<>("Product add to cart successful",HttpStatus.OK);
                        }else{
                            return new ResponseEntity<>("Product add to cart failed",HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    }
                }else {
                    return new ResponseEntity<>("Product or user not found",HttpStatus.NOT_FOUND);
                }
            }else{
                return new ResponseEntity<>("Product ID not found", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new CustomException("Product add to cart failed");
        }
    }

    @Override
    public ResponseEntity<?> getCart(long userId) {
        try{
            Optional<UserEntity> userEntity = userRepository.findById(userId);
            OrderEntity orderEntity = orderRepository.
                    findByUserEntityAndStatus(userEntity.get(), OrderStatus.CART.toString());
            if (orderEntity != null){
                List<OrderDetailEntity> orderDetailList = orderDetailRepository.findByOrderEntity(orderEntity);
                if (!orderDetailList.isEmpty()){
                    List<ProductDTO> productList = new ArrayList<>();
                    for (OrderDetailEntity orderDetailEntity :
                            orderDetailList) {
                        ProductDTO productDTO = EntityToDto.productEntityToDto(orderDetailEntity.getProductEntity());
                        productList.add(productDTO);
                    }
                    return new ResponseEntity<>(productList,HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("No cart for "+userEntity.get().getUsername(),HttpStatus.NOT_FOUND);
                }
            }else {
                return new ResponseEntity<>("No cart for "+userEntity.get().getUsername(),HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            throw new CustomException("Failed to fetch cart");
        }
    }

    private OrderDetailEntity setValuesToOrderDetail(OrderEntity order,
                                                     CartRequestDTO cartRequestDTO,
                                                     ProductEntity productEntity){
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        orderDetailEntity.setQuantity(cartRequestDTO.getQty());
        orderDetailEntity.setOrderPrice(cartRequestDTO.getOrderPrice());
        orderDetailEntity.setOrderDate(DateConverter.localDateToSql(LocalDate.now()));
        orderDetailEntity.setOrderTime(DateConverter.localTimeToSql(LocalTime.now()));
        orderDetailEntity.setProductEntity(productEntity);
        orderDetailEntity.setOrderEntity(order);

        return orderDetailEntity;
    }
}
