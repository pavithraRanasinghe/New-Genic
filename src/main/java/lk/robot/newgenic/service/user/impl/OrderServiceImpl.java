package lk.robot.newgenic.service.user.impl;

import lk.robot.newgenic.dto.Request.BillingDetail;
import lk.robot.newgenic.dto.Request.CartOrderRequestDTO;
import lk.robot.newgenic.dto.Request.OrderRequestDTO;
import lk.robot.newgenic.dto.Request.ShippingDetail;
import lk.robot.newgenic.dto.response.OrderProductDetail;
import lk.robot.newgenic.dto.response.OrderResponseDTO;
import lk.robot.newgenic.entity.*;
import lk.robot.newgenic.enums.AddressType;
import lk.robot.newgenic.enums.OrderStatus;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.*;
import lk.robot.newgenic.service.user.OrderService;
import lk.robot.newgenic.util.DateConverter;
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
public class OrderServiceImpl implements OrderService {

    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private ProductRepository productRepository;
    private PaymentRepository paymentRepository;
    private UserAddressDetailRepository userAddressDetailRepository;
    private UserAddressRepository userAddressRepository;
    private DeliveryRepository deliveryRepository;

    @Autowired
    public OrderServiceImpl(UserRepository userRepository,
                            OrderRepository orderRepository,
                            OrderDetailRepository orderDetailRepository,
                            ProductRepository productRepository,
                            PaymentRepository paymentRepository,
                            UserAddressRepository userAddressRepository,
                            UserAddressDetailRepository userAddressDetailRepository,
                            DeliveryRepository deliveryRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.paymentRepository = paymentRepository;
        this.userAddressRepository = userAddressRepository;
        this.userAddressDetailRepository = userAddressDetailRepository;
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public ResponseEntity<?> placeOrder(OrderRequestDTO orderRequestDTO, long userId) {
        try {
            if (orderRequestDTO != null) {
                Optional<UserEntity> user = userRepository.findById(userId);
                Optional<ProductEntity> productEntity = productRepository.findById(orderRequestDTO.getProductId());
                if (productEntity.isPresent()) {
                    Optional<DeliveryEntity> deliveryEntity = deliveryRepository.findById(orderRequestDTO.getDeliveryId());
                    if (deliveryEntity.isPresent()) {

                        UserAddressEntity billingDetail = userAddressRepository.save(setBillingDetails(orderRequestDTO.getBillingDetail()));
                        UserAddressEntity shippingDetail = userAddressRepository.save(setShippingDetails(orderRequestDTO.getShippingDetail()));
                        UserAddressDetailEntity billingAddressDetail = new UserAddressDetailEntity();
                        UserAddressDetailEntity shippingAddressDetail = new UserAddressDetailEntity();
                        billingAddressDetail.setUserEntity(user.get());
                        billingAddressDetail.setUserAddressEntity(billingDetail);
                        shippingAddressDetail.setUserEntity(user.get());
                        shippingAddressDetail.setUserAddressEntity(shippingDetail);
                        userAddressDetailRepository.save(billingAddressDetail);
                        userAddressDetailRepository.save(shippingAddressDetail);

                        if (billingDetail != null && shippingDetail != null) {
                            PaymentEntity paymentEntity = setPaymentDetails(orderRequestDTO, productEntity.get());
                            PaymentEntity payment = paymentRepository.save(paymentEntity);

                            if (payment != null) {
                                OrderEntity orderEntity = new OrderEntity();
                                orderEntity.setStatus(OrderStatus.PENDING.toString());
                                orderEntity.setOrderDate(DateConverter.localDateToSql(LocalDate.now()));
                                orderEntity.setOrderTime(DateConverter.localTimeToSql(LocalTime.now()));
                                orderEntity.setTotalWeight(productEntity.get().getWeight());
                                orderEntity.setUserEntity(user.get());
                                orderEntity.setDeliveryEntity(deliveryEntity.get());
                                orderEntity.setPaymentEntity(paymentEntity);
                                orderEntity.setShippingDetails(shippingDetail);
                                orderEntity.setBillingDetail(billingDetail);

                                OrderEntity order = orderRepository.save(orderEntity);
                                if (order != null) {
                                    OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
                                    orderDetailEntity.setOrderPrice(orderRequestDTO.getRetailPrice() * orderRequestDTO.getQty());
                                    orderDetailEntity.setQuantity(orderRequestDTO.getQty());
                                    orderDetailEntity.setProductEntity(productEntity.get());
                                    orderDetailEntity.setOrderEntity(order);
                                    OrderDetailEntity save = orderDetailRepository.save(orderDetailEntity);
                                    productEntity.get().setStock(productEntity.get().getStock() - orderRequestDTO.getQty());
                                    productRepository.save(productEntity.get());
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
    public ResponseEntity<?> cartOrderPlace(CartOrderRequestDTO cartOrderRequestDTO, long userId) {
        try {
            Optional<UserEntity> user = userRepository.findById(userId);
            Optional<OrderEntity> cart = orderRepository.findById(cartOrderRequestDTO.getCartId());
            Optional<DeliveryEntity> deliveryEntity = deliveryRepository.findById(cartOrderRequestDTO.getDeliveryId());
            if (cart != null) {
                UserAddressEntity billingDetail = userAddressRepository.save(setBillingDetails(cartOrderRequestDTO.getBillingDetail()));
                UserAddressEntity shippingDetail = userAddressRepository.save(setShippingDetails(cartOrderRequestDTO.getShippingDetail()));
                UserAddressDetailEntity billingAddressDetail = new UserAddressDetailEntity();
                UserAddressDetailEntity shippingAddressDetail = new UserAddressDetailEntity();
                billingAddressDetail.setUserEntity(user.get());
                billingAddressDetail.setUserAddressEntity(billingDetail);
                shippingAddressDetail.setUserEntity(user.get());
                shippingAddressDetail.setUserAddressEntity(shippingDetail);
                userAddressDetailRepository.save(billingAddressDetail);
                userAddressDetailRepository.save(shippingAddressDetail);
                if (billingDetail != null && shippingDetail != null) {
                    PaymentEntity paymentEntity = setCartPaymentDetails(cartOrderRequestDTO);
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

                        OrderEntity save = orderRepository.save(cart.get());
                        if (save != null) {
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
    public ResponseEntity<?> getOrders(long userId) {
        try {
            Optional<UserEntity> user = userRepository.findById(userId);
            List<OrderEntity> orders = orderRepository.findByUserEntity(user.get());
            List<OrderResponseDTO> orderResponseList = new ArrayList<>();
            if (!orders.isEmpty()) {
                for (OrderEntity orderEntity :
                        orders) {
                    List<OrderDetailEntity> orderDetailList = orderDetailRepository.findByOrderEntity(orderEntity);
                    List<OrderProductDetail> productDetailList = new ArrayList<>();
                    for (OrderDetailEntity detailEntity :
                            orderDetailList) {
                        productDetailList.add(setProductDetail(detailEntity));
                    }
                    orderResponseList.add(setOrderDetails(orderEntity, productDetailList));
                }
                return new ResponseEntity<>(orderResponseList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Orders not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException("Failed to load orders");
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

    private PaymentEntity setPaymentDetails(OrderRequestDTO orderRequestDTO, ProductEntity productEntity) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setOrderPrice(orderRequestDTO.getRetailPrice() * orderRequestDTO.getQty());
        paymentEntity.setPaid(false);
        if (productEntity.isFreeShipping()) {
            paymentEntity.setFreeDeliveryPrice(orderRequestDTO.getDeliveryCost());
        } else {
            paymentEntity.setDeliveryPrice(orderRequestDTO.getDeliveryCost());
        }
        return paymentEntity;
    }

    private PaymentEntity setCartPaymentDetails(CartOrderRequestDTO cartOrderRequestDTO) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setOrderPrice(cartOrderRequestDTO.getCartPrice());
        paymentEntity.setPaid(false);
        if (cartOrderRequestDTO.isFreeShipping()) {
            paymentEntity.setFreeDeliveryPrice(cartOrderRequestDTO.getDeliveryCost());
        } else {
            paymentEntity.setDeliveryPrice(cartOrderRequestDTO.getDeliveryCost());
        }
        return paymentEntity;
    }

    private OrderResponseDTO setOrderDetails(OrderEntity orderEntity, List<OrderProductDetail> productDetailList) {
        return new OrderResponseDTO(
                orderEntity.getOrderId(),
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
        return new OrderProductDetail(
                detailEntity.getProductEntity().getProductId(),
                detailEntity.getProductEntity().getName(),
                detailEntity.getProductEntity().getProductCode(),
                detailEntity.getQuantity(),
                detailEntity.getOrderPrice()
        );
    }
}
