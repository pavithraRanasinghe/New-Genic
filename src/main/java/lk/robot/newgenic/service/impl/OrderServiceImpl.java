package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.CombinationDTO;
import lk.robot.newgenic.dto.VariationDTO;
import lk.robot.newgenic.dto.request.BillingDetail;
import lk.robot.newgenic.dto.request.CartOrderRequestDTO;
import lk.robot.newgenic.dto.request.OrderRequestDTO;
import lk.robot.newgenic.dto.request.ShippingDetail;
import lk.robot.newgenic.dto.response.OrderProductDetail;
import lk.robot.newgenic.dto.response.OrderResponseDTO;
import lk.robot.newgenic.dto.response.SingleProductResponseDTO;
import lk.robot.newgenic.entity.*;
import lk.robot.newgenic.enums.AddressType;
import lk.robot.newgenic.enums.OrderStatus;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.*;
import lk.robot.newgenic.service.OrderService;
import lk.robot.newgenic.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transaction;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private ProductRepository productRepository;
    private PaymentRepository paymentRepository;
    private UserAddressDetailRepository userAddressDetailRepository;
    private UserAddressRepository userAddressRepository;
    private DeliveryRepository deliveryRepository;
    private CombinationRepository combinationRepository;
    private VariationCombinationDetailRepository variationCombinationDetailRepository;

    @Autowired
    public OrderServiceImpl(UserRepository userRepository,
                            OrderRepository orderRepository,
                            OrderDetailRepository orderDetailRepository,
                            ProductRepository productRepository,
                            PaymentRepository paymentRepository,
                            UserAddressRepository userAddressRepository,
                            UserAddressDetailRepository userAddressDetailRepository,
                            DeliveryRepository deliveryRepository,
                            CombinationRepository combinationRepository,
                            VariationCombinationDetailRepository variationCombinationDetailRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.paymentRepository = paymentRepository;
        this.userAddressRepository = userAddressRepository;
        this.userAddressDetailRepository = userAddressDetailRepository;
        this.deliveryRepository = deliveryRepository;
        this.combinationRepository = combinationRepository;
        this.variationCombinationDetailRepository = variationCombinationDetailRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<?> placeOrder(OrderRequestDTO orderRequestDTO, String userId) {
        try {
            if (orderRequestDTO != null) {
                Optional<UserEntity> user = userRepository.findByUserUuid(userId);
                Optional<CombinationEntity> combinationEntity = combinationRepository.findById(orderRequestDTO.getCombinationId());
                if (combinationEntity.isPresent()) {

                    Optional<DeliveryEntity> deliveryEntity = deliveryRepository.findById(orderRequestDTO.getDeliveryId());
                    if (deliveryEntity.isPresent()) {

                        UserAddressEntity billingDetail = setBillingDetails(orderRequestDTO.getBillingDetail());
                        UserAddressEntity shippingDetail = setShippingDetails(orderRequestDTO.getShippingDetail());

                        if (billingDetail != null && shippingDetail != null) {
                            PaymentEntity paymentEntity = setPaymentDetails(orderRequestDTO, combinationEntity.get());
                            PaymentEntity payment = paymentRepository.save(paymentEntity);

                            if (payment != null) {
                                OrderEntity orderEntity = new OrderEntity();
                                orderEntity.setOrderUuid(UUID.randomUUID().toString());
                                orderEntity.setStatus(OrderStatus.PENDING.toString());
                                orderEntity.setOrderDate(DateConverter.localDateToSql(LocalDate.now()));
                                orderEntity.setOrderTime(DateConverter.localTimeToSql(LocalTime.now()));
                                orderEntity.setTotalWeight(combinationEntity.get().getWeight());
                                orderEntity.setUserEntity(user.get());
                                orderEntity.setDeliveryEntity(deliveryEntity.get());
                                orderEntity.setPaymentEntity(paymentEntity);
                                orderEntity.setShippingDetails(shippingDetail);
                                orderEntity.setBillingDetail(billingDetail);

                                OrderEntity order = orderRepository.save(orderEntity);
                                if (order != null) {
                                    OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
                                    orderDetailEntity.setOrderPrice(combinationEntity.get().getRetailPrice() * orderRequestDTO.getQty());
                                    orderDetailEntity.setQuantity(orderRequestDTO.getQty());
                                    orderDetailEntity.setCombinationEntity(combinationEntity.get());
                                    orderDetailEntity.setOrderEntity(order);
                                    OrderDetailEntity save = orderDetailRepository.save(orderDetailEntity);
                                    combinationEntity.get().setStock(combinationEntity.get().getStock() - orderRequestDTO.getQty());
                                    combinationRepository.save(combinationEntity.get());
                                    if (save != null) {
                                        return new ResponseEntity<>("Order place successful", HttpStatus.OK);
                                    } else {
                                        return new ResponseEntity<>("Order detail not saved", HttpStatus.BAD_REQUEST);
                                    }
                                } else {
                                    return new ResponseEntity<>("Order not saved", HttpStatus.BAD_REQUEST);
                                }
                            } else {
                                return new ResponseEntity<>("Payment not saved", HttpStatus.BAD_REQUEST);
                            }
                        } else {
                            return new ResponseEntity<>("Billing or Shipping details not saved", HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        return new ResponseEntity<>("Delivery not found", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Order details not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException("Place order failed");
        }
    }

    @Override
    public ResponseEntity<?> cartOrderPlace(CartOrderRequestDTO cartOrderRequestDTO, String userId) {
        try {
            Optional<UserEntity> user = userRepository.findByUserUuid(userId);
            Optional<OrderEntity> cart = orderRepository.findByOrderUuid(cartOrderRequestDTO.getCartId());
            Optional<DeliveryEntity> deliveryEntity = deliveryRepository.findById(cartOrderRequestDTO.getDeliveryId());
            if (cart.isPresent()) {
                UserAddressEntity billingDetail = setBillingDetails(cartOrderRequestDTO.getBillingDetail());
                UserAddressEntity shippingDetail = setShippingDetails(cartOrderRequestDTO.getShippingDetail());

                if (billingDetail != null && shippingDetail != null) {
                    PaymentEntity paymentEntity = setCartPaymentDetails(cartOrderRequestDTO, cart.get());
                    PaymentEntity payment = paymentRepository.save(paymentEntity);

                    if (payment != null) {
                        cart.get().setStatus(OrderStatus.PENDING.toString());
                        cart.get().setOrderDate(DateConverter.localDateToSql(LocalDate.now()));
                        cart.get().setOrderTime(DateConverter.localTimeToSql(LocalTime.now()));
                        cart.get().setTotalWeight(cartOrderRequestDTO.getTotalWeight());
                        cart.get().setUserEntity(user.get());
                        cart.get().setDeliveryEntity(deliveryEntity.get());
                        cart.get().setPaymentEntity(payment);
                        cart.get().setBillingDetail(billingDetail);
                        cart.get().setShippingDetails(shippingDetail);

                        OrderEntity order = orderRepository.save(cart.get());
                        if (order != null) {
                            List<OrderDetailEntity> orderDetailList = orderDetailRepository.findByOrderEntity(cart.get());
                            if (!orderDetailList.isEmpty()) {
                                for (OrderDetailEntity orderDetailEntity :
                                        orderDetailList) {
                                    CombinationEntity combinationEntity = orderDetailEntity.getCombinationEntity();
                                    combinationEntity.setStock(combinationEntity.getStock() - orderDetailEntity.getQuantity());
                                    combinationRepository.save(combinationEntity);
                                }
                            }
                            return new ResponseEntity<>("Order successful", HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>("Order not saved", HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        return new ResponseEntity<>("Payment not saved", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return new ResponseEntity<>("Billing or Shipping details not saved", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Cart not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException("Cart Order failed");
        }
    }

    @Override
    public ResponseEntity<?> getOrders(String userId) {
        try {
            Optional<UserEntity> user = userRepository.findByUserUuid(userId);
            if (user.isPresent()){
                List<OrderEntity> orders = orderRepository.findByUserEntity(user.get());
                List<OrderResponseDTO> orderResponseList = new ArrayList<>();
                if (!orders.isEmpty()) {
                    for (OrderEntity orderEntity :
                            orders) {
                        if (!orderEntity.getStatus().equals(OrderStatus.CART.toString())){
                            List<OrderDetailEntity> orderDetailList = orderDetailRepository.findByOrderEntity(orderEntity);
                            List<OrderProductDetail> productDetailList = new ArrayList<>();
                            for (OrderDetailEntity detailEntity :
                                    orderDetailList) {
                                productDetailList.add(setProductDetail(detailEntity));
                            }
                            orderResponseList.add(setOrderDetails(orderEntity, productDetailList));
                        }
                    }
                    return new ResponseEntity<>(orderResponseList, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Orders not found", HttpStatus.NOT_FOUND);
                }
            }else {
                return new ResponseEntity<>("User not found",HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    private UserAddressEntity setBillingDetails(BillingDetail billingDetail) {
        UserAddressEntity userAddressEntity = new UserAddressEntity();
        userAddressEntity.setFirstName(billingDetail.getFirstName());
        userAddressEntity.setLastName(billingDetail.getLastName());
        userAddressEntity.setAddress(billingDetail.getAddress());
        userAddressEntity.setDistrict(billingDetail.getDistrict());
        userAddressEntity.setCity(billingDetail.getCity());
        userAddressEntity.setMobile(billingDetail.getMobile());
        userAddressEntity.setType(AddressType.BILLING.toString());

        return userAddressEntity;
    }

    private UserAddressEntity setShippingDetails(ShippingDetail shippingDetail) {
        UserAddressEntity userAddressEntity = new UserAddressEntity();
        userAddressEntity.setFirstName(shippingDetail.getFirstName());
        userAddressEntity.setLastName(shippingDetail.getLastName());
        userAddressEntity.setAddress(shippingDetail.getAddress());
        userAddressEntity.setDistrict(shippingDetail.getDistrict());
        userAddressEntity.setCity(shippingDetail.getCity());
        userAddressEntity.setMobile(shippingDetail.getMobile());
        userAddressEntity.setPostalCode(shippingDetail.getPostalCode());
        userAddressEntity.setType(AddressType.SHIPPING.toString());

        return userAddressEntity;
    }

    private PaymentEntity setPaymentDetails(OrderRequestDTO orderRequestDTO, CombinationEntity combinationEntity) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setOrderPrice(combinationEntity.getRetailPrice() * orderRequestDTO.getQty());
        paymentEntity.setBuyingPrice(combinationEntity.getBuyingPrice() * orderRequestDTO.getQty());
        paymentEntity.setPaid(false);

        ProductEntity product = getProductFromCombination(combinationEntity);
        if (product.isFreeShipping()) {
            paymentEntity.setFreeDeliveryPrice(orderRequestDTO.getDeliveryCost());
        } else {
            paymentEntity.setDeliveryPrice(orderRequestDTO.getDeliveryCost());
        }
        return paymentEntity;
    }

    private PaymentEntity setCartPaymentDetails(CartOrderRequestDTO cartOrderRequestDTO, OrderEntity orderEntity) {
        List<OrderDetailEntity> orderDetailList = orderDetailRepository.findByOrderEntity(orderEntity);
        PaymentEntity paymentEntity = new PaymentEntity();
        if (!orderDetailList.isEmpty()){
            double totalPrice = 0;
            double buyingPrice = 0;
            for (OrderDetailEntity orderDetailEntity :
                    orderDetailList) {
                totalPrice += orderDetailEntity.getCombinationEntity().getRetailPrice() + orderDetailEntity.getQuantity();
                buyingPrice += orderDetailEntity.getCombinationEntity().getBuyingPrice() + orderDetailEntity.getQuantity();
            }
            paymentEntity.setOrderPrice(totalPrice);
            paymentEntity.setBuyingPrice(buyingPrice);
            paymentEntity.setPaid(false);
            if (cartOrderRequestDTO.isFreeShipping()) {
                paymentEntity.setFreeDeliveryPrice(cartOrderRequestDTO.getDeliveryCost());
            } else {
                paymentEntity.setDeliveryPrice(cartOrderRequestDTO.getDeliveryCost());
            }
        }
        return paymentEntity;
    }

    private OrderResponseDTO setOrderDetails(OrderEntity orderEntity, List<OrderProductDetail> productDetailList) {
        return new OrderResponseDTO(
                orderEntity.getOrderUuid(),
                orderEntity.getOrderDate(),
                orderEntity.getOrderTime(),
                orderEntity.getTrackingNumber(),
                orderEntity.getStatus(),
                orderEntity.getPaymentEntity().getOrderPrice(),
                orderEntity.getPaymentEntity().getDiscountPrice(),
                orderEntity.getPaymentEntity().getDeliveryPrice(),
                productDetailList
        );
    }

    private OrderProductDetail setProductDetail(OrderDetailEntity detailEntity) {
        CombinationEntity combinationEntity = detailEntity.getCombinationEntity();
        ProductEntity productEntity = getProductFromCombination(combinationEntity);
        OrderProductDetail orderProductDetail = new OrderProductDetail();
        orderProductDetail.setProductId(productEntity.getUuid());
        orderProductDetail.setProductCode(productEntity.getProductCode());
        orderProductDetail.setProductName(productEntity.getName());
        orderProductDetail.setQty(detailEntity.getQuantity());
        orderProductDetail.setOrderPrice(detailEntity.getOrderPrice());

        CombinationDTO combinationDTO = new CombinationDTO();
        combinationDTO.setCombinationId(combinationEntity.getCombinationId());
        combinationDTO.setWeight(combinationEntity.getWeight());
        combinationDTO.setStock(combinationEntity.getStock());
        combinationDTO.setSalePrice(combinationEntity.getSalePrice());
        combinationDTO.setRetailPrice(combinationEntity.getRetailPrice());

        List<VariationDTO> variationDTOList = setVariationsFromCombination(combinationEntity);
        combinationDTO.setVariationList(variationDTOList);
        orderProductDetail.setCombinationDTO(combinationDTO);

        return orderProductDetail;
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

    private UserAddressEntity setBilling(BillingDetail billingDetailDTO,UserEntity userEntity){
        UserAddressEntity billingDetail = userAddressRepository.save(setBillingDetails(billingDetailDTO));
        UserAddressDetailEntity billingAddressDetail = new UserAddressDetailEntity();
        billingAddressDetail.setUserEntity(userEntity);
        billingAddressDetail.setUserAddressEntity(billingDetail);
        userAddressDetailRepository.save(billingAddressDetail);

        return billingDetail;
    }
    private UserAddressEntity setShipping(ShippingDetail shippingDetailDTO,UserEntity userEntity) {
        UserAddressEntity shippingDetail = userAddressRepository.save(setShippingDetails(shippingDetailDTO));
        UserAddressDetailEntity shippingAddressDetail = new UserAddressDetailEntity();
        shippingAddressDetail.setUserEntity(userEntity);
        shippingAddressDetail.setUserAddressEntity(shippingDetail);
        userAddressDetailRepository.save(shippingAddressDetail);

        return shippingDetail;
    }

    private List<VariationDTO> setVariationsFromCombination(CombinationEntity combinationEntity){
        List<VariationDTO> variationList = new ArrayList<>();
        List<VariationCombinationDetailEntity> variationCombinationList = variationCombinationDetailRepository.findByCombinationEntity(combinationEntity);
        for (VariationCombinationDetailEntity variationCombinationDetailEntity :
                variationCombinationList) {
            variationList.add(new VariationDTO(
                    variationCombinationDetailEntity.getVariationDetailEntity().getVariationEntity().getVariationId(),
                    variationCombinationDetailEntity.getVariationDetailEntity().getVariationEntity().getVariationName(),
                    variationCombinationDetailEntity.getVariationDetailEntity().getVariationDetailId(),
                    variationCombinationDetailEntity.getVariationDetailEntity().getValue()
            ));
        }
        return variationList;
    }
}
