package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.request.ReturnRequestDTO;
import lk.robot.newgenic.repository.*;
import lk.robot.newgenic.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ResponseEntity<?> returnRequest(List<ReturnRequestDTO> returnRequestDTOList, long userId) {
//        try {
//            if (!returnRequestDTOList.isEmpty()) {
//                for (ReturnRequestDTO returnRequestDTO :
//                        returnRequestDTOList) {
//                    Optional<OrderEntity> order = orderRepository.findById(returnRequestDTO.getOrderId());
//                    Optional<ProductEntity> product = productRepository.findById(returnRequestDTO.getProductId());
//                    if (order.isPresent() && product.isPresent()) {
//                        OrderDetailEntity orderDetailEntity = orderDetailRepository.findByOrderEntityAndProductEntity(order.get(), product.get());
//                        if (orderDetailEntity != null) {
//                            ReturnEntity returnEntity = new ReturnEntity();
//                            returnEntity.setReason(returnRequestDTO.getReason());
//                            returnEntity.setRequestDate(DateConverter.localDateToSql(LocalDate.now()));
//                            returnEntity.setRequestTime(DateConverter.localTimeToSql(LocalTime.now()));
//                            returnEntity.setReturnQty(returnRequestDTO.getReturnQty());
//                            returnEntity.setAction(ReturnAction.PENDING.toString());
//
//                            ReturnEntity returnSaved = returnRepository.save(returnEntity);
//                            if (returnSaved != null) {
//                                orderDetailEntity.setReturnEntity(returnSaved);
//                                orderDetailRepository.save(orderDetailEntity);
//                            } else {
//                                return new ResponseEntity<>("Return request not sent", HttpStatus.BAD_REQUEST);
//                            }
//                        } else {
//                            return new ResponseEntity<>("Order Detail not found", HttpStatus.NOT_FOUND);
//                        }
//                    } else {
//                        return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
//                    }
//                }
//                return new ResponseEntity<>("Return request sent", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Return request details not found", HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            throw new CustomException("Return request failed");
//        }

        return null;
    }

    @Override
    public ResponseEntity<?> getReturn(long userId) {
//        try {
//            Optional<UserEntity> user = userRepository.findById(userId);
//            List<OrderEntity> orderList = orderRepository.findByUserEntity(user.get());
//            List<ReturnResponseDTO> responseList = new ArrayList<>();
//            if (!orderList.isEmpty()) {
//                for (OrderEntity orderEntity :
//                        orderList) {
//                    List<OrderDetailEntity> orderDetailList = orderDetailRepository.findByOrderEntity(orderEntity);
//                    for (OrderDetailEntity orderDetailEntity :
//                            orderDetailList) {
//                        ReturnEntity returnEntity = orderDetailEntity.getReturnEntity();
//                        if (returnEntity != null) {
//                            responseList.add(setReturnDetails(returnEntity, orderDetailEntity));
//                        }
//                    }
//                }
//                return new ResponseEntity<>(responseList, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("No orders for you", HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            throw new CustomException("Failed to load returns");
//        }
        return null;
    }

//    private ReturnResponseDTO setReturnDetails(ReturnEntity returnEntity, OrderDetailEntity detailEntity) {
//        return new ReturnResponseDTO(
//                returnEntity.getReturnRequestId(),
//                returnEntity.getReason(),
//                returnEntity.getRequestDate(),
//                returnEntity.getRequestTime(),
//                returnEntity.getAction(),
//                detailEntity.getProductEntity().getProductId(),
//                detailEntity.getOrderEntity().getOrderId(),
//                detailEntity.getProductEntity().getName(),
//                detailEntity.getQuantity(),
//                detailEntity.getOrderPrice()
//        );
//    }
}
