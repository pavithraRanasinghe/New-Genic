package lk.robot.newgenic.service.user.impl;

import lk.robot.newgenic.dto.Request.ReturnRequestDTO;
import lk.robot.newgenic.dto.response.ReturnResponseDTO;
import lk.robot.newgenic.entity.*;
import lk.robot.newgenic.enums.ReturnAction;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.*;
import lk.robot.newgenic.service.user.ReturnService;
import lk.robot.newgenic.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReturnServiceImpl implements ReturnService {

    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private ProductRepository productRepository;
    private ReturnRepository returnRepository;

    @Autowired
    public ReturnServiceImpl(UserRepository userRepository,
                             OrderDetailRepository orderDetailRepository,
                             OrderRepository orderRepository,
                             ProductRepository productRepository,
                             ReturnRepository returnRepository) {
        this.userRepository = userRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.returnRepository = returnRepository;
    }

    @Override
    public ResponseEntity<?> returnRequest(ReturnRequestDTO returnRequestDTO, long userId) {
        try {
            if (returnRequestDTO != null) {
                Optional<UserEntity> user = userRepository.findById(userId);
                Optional<OrderEntity> order = orderRepository.findById(returnRequestDTO.getOrderId());
                Optional<ProductEntity> product = productRepository.findById(returnRequestDTO.getProductId());
                if (order.isPresent() && product.isPresent()) {
                    OrderDetailEntity orderDetailEntity = orderDetailRepository.findByOrderEntityAndProductEntity(order.get(), product.get());
                    if (orderDetailEntity != null) {
                        ReturnEntity returnEntity = new ReturnEntity();
                        returnEntity.setReason(returnRequestDTO.getReason());
                        returnEntity.setRequestDate(DateConverter.localDateToSql(LocalDate.now()));
                        returnEntity.setRequestTime(DateConverter.localTimeToSql(LocalTime.now()));
                        returnEntity.setAction(ReturnAction.PENDING.toString());

                        ReturnEntity returnSaved = returnRepository.save(returnEntity);
                        if (returnSaved != null) {
                            orderDetailEntity.setReturnEntity(returnSaved);
                            orderDetailRepository.save(orderDetailEntity);
                            return new ResponseEntity<>("Return request sent", HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>("Return request not sent", HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        return new ResponseEntity<>("Order Detail not found", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Return request details not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException("Return request failed");
        }
    }

    @Override
    public ResponseEntity<?> getReturn(long userId) {
        try {
            Optional<UserEntity> user = userRepository.findById(userId);
            List<OrderEntity> orderList = orderRepository.findByUserEntity(user.get());
            List<ReturnResponseDTO> responseList = new ArrayList<>();
            if (!orderList.isEmpty()) {
                for (OrderEntity orderEntity :
                        orderList) {
                    List<OrderDetailEntity> orderDetailList = orderDetailRepository.findByOrderEntity(orderEntity);
                    for (OrderDetailEntity orderDetailEntity :
                            orderDetailList) {
                        ReturnEntity returnEntity = orderDetailEntity.getReturnEntity();
                        if (returnEntity != null) {
                            responseList.add(setReturnDetails(returnEntity, orderDetailEntity));
                        }
                    }
                }
                return new ResponseEntity<>(responseList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No orders for you", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException("Failed to load returns");
        }
    }

    private ReturnResponseDTO setReturnDetails(ReturnEntity returnEntity, OrderDetailEntity detailEntity) {
        return new ReturnResponseDTO(
                returnEntity.getReturnRequestId(),
                returnEntity.getReason(),
                returnEntity.getRequestDate(),
                returnEntity.getRequestTime(),
                returnEntity.getAction(),
                detailEntity.getProductEntity().getProductId(),
                detailEntity.getOrderEntity().getOrderId(),
                detailEntity.getProductEntity().getName(),
                detailEntity.getQuantity(),
                detailEntity.getOrderPrice()
        );
    }
}
