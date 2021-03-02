package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.ReturnDetailDTO;
import lk.robot.newgenic.dto.request.ReturnRequestDTO;
import lk.robot.newgenic.dto.response.ReturnResponseDTO;
import lk.robot.newgenic.entity.*;
import lk.robot.newgenic.enums.ReturnAction;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.*;
import lk.robot.newgenic.service.ReturnService;
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
    private CombinationRepository combinationRepository;
    private ReturnDetailRepository returnDetailRepository;
    private VariationCombinationDetailRepository variationCombinationDetailRepository;

    @Autowired
    public ReturnServiceImpl(UserRepository userRepository,
                             OrderDetailRepository orderDetailRepository,
                             OrderRepository orderRepository,
                             ProductRepository productRepository,
                             ReturnRepository returnRepository,
                             CombinationRepository combinationRepository,
                             ReturnDetailRepository returnDetailRepository,
                             VariationCombinationDetailRepository variationCombinationDetailRepository) {
        this.userRepository = userRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.returnRepository = returnRepository;
        this.combinationRepository = combinationRepository;
        this.returnDetailRepository = returnDetailRepository;
        this.variationCombinationDetailRepository = variationCombinationDetailRepository;
    }

    @Override
    public ResponseEntity<?> returnRequest(ReturnRequestDTO returnRequest, String userId) {
        try {
            if (returnRequest != null) {
                for (ReturnDetailDTO returnDetailDTO :
                        returnRequest.getRequestList()) {
                    Optional<OrderEntity> order = orderRepository.findByOrderUuid(returnRequest.getOrderId());
                    Optional<CombinationEntity> combinationEntity = combinationRepository.findById(returnDetailDTO.getCombinationId());
                    if (order.isPresent() && combinationEntity.isPresent()) {
                        if (order.get().getReturnEntity() == null) {
                            OrderDetailEntity orderDetailEntity = orderDetailRepository.findByOrderEntityAndCombinationEntity(order.get(), combinationEntity.get());
                            if (orderDetailEntity != null) {
                                if (orderDetailEntity.getQuantity() >= returnDetailDTO.getQty()) {
                                    ReturnEntity returnEntity = new ReturnEntity();
                                    returnEntity.setRequestDate(DateConverter.localDateToSql(LocalDate.now()));
                                    returnEntity.setRequestTime(DateConverter.localTimeToSql(LocalTime.now()));
                                    returnEntity.setAction(ReturnAction.PENDING.toString());
                                    ReturnEntity returnSaved = returnRepository.save(returnEntity);
                                    if (returnSaved != null) {
                                        ReturnDetailEntity returnDetailEntity = new ReturnDetailEntity();
                                        returnDetailEntity.setReason(returnDetailDTO.getReason());
                                        returnDetailEntity.setReturnQty(returnDetailDTO.getQty());
                                        returnDetailEntity.setReturnEntity(returnSaved);

                                        ReturnDetailEntity returnDetail = returnDetailRepository.save(returnDetailEntity);
                                        if (returnDetail != null) {
                                            orderDetailEntity.setReturnDetailEntity(returnDetail);

                                            orderDetailRepository.save(orderDetailEntity);
                                            order.get().setReturnEntity(returnSaved);
                                            orderRepository.save(order.get());
                                        } else {
                                            return new ResponseEntity<>("Return detail request not save", HttpStatus.BAD_REQUEST);
                                        }

                                    } else {
                                        return new ResponseEntity<>("Return request not save", HttpStatus.BAD_REQUEST);
                                    }
                                }
                            } else {
                                return new ResponseEntity<>("Order Detail not found", HttpStatus.NOT_FOUND);
                            }
                        }else {
                            return new ResponseEntity<>("Order already returned",HttpStatus.ALREADY_REPORTED);
                        }
                    } else {
                        return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
                    }
                }
                return new ResponseEntity<>("Return request sent", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Return request details not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException("Return request failed");
        }
    }

    @Override
    public ResponseEntity<?> getReturn(String userId) {
        try {
            Optional<UserEntity> user = userRepository.findByUserUuid(userId);
            List<OrderEntity> orderList = orderRepository.findByUserEntity(user.get());
            List<ReturnResponseDTO> responseList = new ArrayList<>();
            if (!orderList.isEmpty()) {
                for (OrderEntity orderEntity :
                        orderList) {
                    List<OrderDetailEntity> orderDetailList = orderDetailRepository.findByOrderEntity(orderEntity);
                    for (OrderDetailEntity orderDetailEntity :
                            orderDetailList) {
                        ReturnDetailEntity returnEntity = orderDetailEntity.getReturnDetailEntity();
                        if (returnEntity != null) {
                            ReturnResponseDTO returnResponseDTO = setReturnDetails(returnEntity, orderDetailEntity);
                            if (returnResponseDTO != null) {
                                responseList.add(returnResponseDTO);
                            }
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

    private ReturnResponseDTO setReturnDetails(ReturnDetailEntity returnEntity, OrderDetailEntity detailEntity) {

        ProductEntity product = getProductFromCombination(detailEntity.getCombinationEntity());
        if (product == null) {
            return null;
        } else {
            return new ReturnResponseDTO(
                    returnEntity.getReturnEntity().getReturnRequestId(),
                    returnEntity.getReason(),
                    returnEntity.getReturnEntity().getRequestDate(),
                    returnEntity.getReturnEntity().getRequestTime(),
                    returnEntity.getReturnEntity().getAction(),
                    detailEntity.getCombinationEntity().getCombinationId(),
                    detailEntity.getOrderEntity().getOrderId(),
                    product.getName(),
                    detailEntity.getQuantity(),
                    detailEntity.getOrderPrice()
            );
        }
    }

    private ProductEntity getProductFromCombination(CombinationEntity combinationEntity) {
        List<VariationCombinationDetailEntity> variationCombinationList = variationCombinationDetailRepository.findByCombinationEntity(combinationEntity);
        if (!variationCombinationList.isEmpty()) {
            ProductEntity productEntity = new ProductEntity();
            for (VariationCombinationDetailEntity variationCombinationDetailEntity :
                    variationCombinationList) {
                productEntity = variationCombinationDetailEntity.getVariationDetailEntity().getVariationEntity().getProductEntity();
            }
            return productEntity;
        } else {
            return null;
        }
    }
}
