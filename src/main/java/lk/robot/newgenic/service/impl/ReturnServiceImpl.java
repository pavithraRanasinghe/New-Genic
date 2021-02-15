package lk.robot.newgenic.service.impl;

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
    public ResponseEntity<?> returnRequest(List<ReturnRequestDTO> returnRequestDTOList, String userId) {
        try {
            if (!returnRequestDTOList.isEmpty()) {
                for (ReturnRequestDTO returnRequestDTO :
                        returnRequestDTOList) {
                    Optional<OrderEntity> order = orderRepository.findByOrderUuid(returnRequestDTO.getOrderId());
                    Optional<CombinationEntity> combinationEntity = combinationRepository.findById(returnRequestDTO.getCombinationId());
                    if (order.isPresent() && combinationEntity.isPresent()) {
                        OrderDetailEntity orderDetailEntity = orderDetailRepository.findByOrderEntityAndCombinationEntity(order.get(), combinationEntity.get());
                        if (orderDetailEntity != null) {
                            ReturnEntity returnEntity = new ReturnEntity();
                            returnEntity.setRequestDate(DateConverter.localDateToSql(LocalDate.now()));
                            returnEntity.setRequestTime(DateConverter.localTimeToSql(LocalTime.now()));
                            returnEntity.setAction(ReturnAction.PENDING.toString());
                            ReturnEntity returnSaved = returnRepository.save(returnEntity);
                            if (returnSaved != null) {
                                ReturnDetailEntity returnDetailEntity = new ReturnDetailEntity();
                                returnDetailEntity.setReason(returnRequestDTO.getReason());
                                returnDetailEntity.setReturnQty(returnRequestDTO.getReturnQty());
                                returnDetailEntity.setReturnEntity(returnSaved);

                                ReturnDetailEntity detailEntity = returnDetailRepository.save(returnDetailEntity);
                                if (detailEntity != null){
                                    orderDetailEntity.setReturnDetailEntity(detailEntity);

                                    orderDetailRepository.save(orderDetailEntity);
                                }else {
                                    return new ResponseEntity<>("Return detail request not save", HttpStatus.BAD_REQUEST);
                                }
                            } else {
                                return new ResponseEntity<>("Return request not save", HttpStatus.BAD_REQUEST);
                            }
                        } else {
                            return new ResponseEntity<>("Order Detail not found", HttpStatus.NOT_FOUND);
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
                            if (returnResponseDTO != null){
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
        if (product == null){
            return null;
        }else{
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
