package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.*;
import lk.robot.newgenic.dto.request.CartRequestDTO;
import lk.robot.newgenic.dto.request.CartUpdateRequestDTO;
import lk.robot.newgenic.dto.response.*;
import lk.robot.newgenic.entity.*;
import lk.robot.newgenic.enums.DiscountType;
import lk.robot.newgenic.enums.OrderStatus;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.*;
import lk.robot.newgenic.service.CartService;
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
import java.util.UUID;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private DiscountMethodRepository discountMethodRepository;
    private DeliveryRepository deliveryRepository;
    private CombinationRepository combinationRepository;
    private VariationCombinationDetailRepository variationCombinationDetailRepository;

    @Autowired
    public CartServiceImpl(
            ProductRepository productRepository,
            OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository,
            UserRepository userRepository,
            DiscountMethodRepository discountMethodRepository,
            DeliveryRepository deliveryRepository,
            CombinationRepository combinationRepository,
            VariationCombinationDetailRepository variationCombinationDetailRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.userRepository = userRepository;
        this.discountMethodRepository = discountMethodRepository;
        this.deliveryRepository = deliveryRepository;
        this.combinationRepository = combinationRepository;
        this.variationCombinationDetailRepository = variationCombinationDetailRepository;
    }

    @Override
    public ResponseEntity<?> addToCart(CartRequestDTO cartRequestDTO, String userId) {
        try {
            if (cartRequestDTO.getCombinationId() != 0) {
                Optional<UserEntity> userEntity = userRepository.findByUserUuid(userId);
                Optional<CombinationEntity> combinationEntity = combinationRepository.findById(cartRequestDTO.getCombinationId());
                if (userEntity.isPresent() && combinationEntity.isPresent()) {
                    OrderEntity existCart = orderRepository.findByUserEntityAndStatus(userEntity.get(),
                            OrderStatus.CART.toString());
                    ProductEntity productEntity = getProductFromCombination(combinationEntity.get());
                    if (existCart != null) {
                        OrderDetailEntity existProduct = orderDetailRepository.
                                findByOrderEntityAndCombinationEntity(existCart, combinationEntity.get());
                        if (existProduct != null) {
                            return new ResponseEntity<>("Product already added to cart", HttpStatus.BAD_REQUEST);
                        }
                        existCart.setTotalWeight(existCart.getTotalWeight() + combinationEntity.get().getWeight());
                        OrderEntity order = orderRepository.save(existCart);
                        if (!order.equals(null)) {
                            OrderDetailEntity orderDetailEntity = setValuesToOrderDetail(order, cartRequestDTO, combinationEntity.get());

                            OrderDetailEntity save = orderDetailRepository.save(orderDetailEntity);
                            if (save != null) {
                                return new ResponseEntity<>("Product add to cart successful", HttpStatus.OK);
                            } else {
                                return new ResponseEntity<>("Order details not saved", HttpStatus.BAD_REQUEST);
                            }

                        } else {
                            return new ResponseEntity<>("Product add to cart failed", HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    } else {
                        OrderEntity orderEntity = new OrderEntity();
                        orderEntity.setStatus(OrderStatus.CART.toString());
                        orderEntity.setTotalWeight(combinationEntity.get().getWeight());
                        orderEntity.setUserEntity(userEntity.get());
                        orderEntity.setOrderUuid(UUID.randomUUID().toString());
                        orderEntity.setOrderDate(DateConverter.localDateToSql(LocalDate.now()));
                        orderEntity.setOrderTime(DateConverter.localTimeToSql(LocalTime.now()));
                        OrderEntity order = orderRepository.save(orderEntity);
                        if (!order.equals(null)) {
                            OrderDetailEntity orderDetailEntity = setValuesToOrderDetail(order, cartRequestDTO, combinationEntity.get());
                            OrderDetailEntity save = orderDetailRepository.save(orderDetailEntity);

                            if (save != null) {
                                return new ResponseEntity<>("Product add to cart successful", HttpStatus.OK);
                            } else {
                                return new ResponseEntity<>("Order details not saved", HttpStatus.BAD_REQUEST);
                            }
                        } else {
                            return new ResponseEntity<>("Product add to cart failed", HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    }
                } else {
                    return new ResponseEntity<>("Product or user not found", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Product ID not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException("Product add to cart failed");
        }
    }

    @Override
    public ResponseEntity<?> getCart(String userId) {
        try {
            Optional<UserEntity> userEntity = userRepository.findByUserUuid(userId);
            OrderEntity orderEntity = orderRepository.
                    findByUserEntityAndStatus(userEntity.get(), OrderStatus.CART.toString());
            if (orderEntity != null) {
                List<OrderDetailEntity> orderDetailList = orderDetailRepository.findByOrderEntity(orderEntity);
                if (!orderDetailList.isEmpty()) {
                    CartResponseDTO cartResponseDTO = setCart(orderDetailList);
                    return new ResponseEntity<>(cartResponseDTO, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("No cart for " + userEntity.get().getUsername(), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("No cart for " + userEntity.get().getUsername(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {

            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> cartOrder(String userId) {
        try {
            Optional<UserEntity> userEntity = userRepository.findByUserUuid(userId);
            OrderEntity orderEntity = orderRepository.
                    findByUserEntityAndStatus(userEntity.get(), OrderStatus.CART.toString());
            List<OrderDetailEntity> orderDetailList = orderDetailRepository.findByOrderEntity(orderEntity);
            CartResponseDTO cartResponseDTO = setCart(orderDetailList);
            List<DeliveryEntity> allDeliveries = deliveryRepository.findAll();
            CartOrderResponse cartOrderResponse = new CartOrderResponse();
            double cartPrice = 0;
            double totalWeight = 0;
            for (SingleProductResponseDTO productDTO :
                    cartResponseDTO.getProductList()) {
                cartPrice = cartPrice + productDTO.getCombinationDTO().getRetailPrice();
                totalWeight = totalWeight + productDTO.getCombinationDTO().getWeight();
                if (!productDTO.isFreeShipping()) {
                    cartOrderResponse.setCartFreeShipping(false);
                }
            }
            DiscountMethodEntity discountMethod = discountMethodRepository.getDiscountMethod();
            if (discountMethod != null && discountMethod.getPriceLimit() <= cartPrice) {
                if (discountMethod.getType().equals(DiscountType.DISCOUNT)) {
                    cartOrderResponse.setDiscount(cartPrice * discountMethod.getDiscount() / 100);
                    cartOrderResponse.setCartFreeShipping(false);
                } else if (discountMethod.getType().equals(DiscountType.FREE_SHIPPING)) {
                    cartOrderResponse.setCartFreeShipping(true);
                }
            }

            List<DeliveryDTO> deliveryDTOList = setDeliveryEntityToDto(allDeliveries);
            cartOrderResponse.setDeliveryDTOList(deliveryDTOList);
            cartOrderResponse.setProductDTOList(cartResponseDTO.getProductList());
            cartOrderResponse.setCartPrice(cartPrice);
            cartOrderResponse.setTotalWeight(totalWeight);
            return new ResponseEntity<>(cartOrderResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateCart(CartUpdateRequestDTO cartUpdateRequestDTO,String userId) {
        try{
            Optional<UserEntity> user = userRepository.findByUserUuid(userId);
            if (cartUpdateRequestDTO != null && user.isPresent()){
                Optional<OrderEntity> order = orderRepository.findByOrderUuid(cartUpdateRequestDTO.getOrderId());
                if (order.isPresent() && order.get().getUserEntity().equals(user.get())){
                    if (!cartUpdateRequestDTO.getUpdateDetailRequestList().isEmpty()){
                        for (CartUpdateDetailRequestDTO detailRequestDTO :
                                cartUpdateRequestDTO.getUpdateDetailRequestList()) {
                            Optional<CombinationEntity> combination = combinationRepository.findById(detailRequestDTO.getCombinationId());
                            OrderDetailEntity orderDetail = orderDetailRepository.findByOrderEntityAndCombinationEntity(order.get(), combination.get());
                            if (orderDetail != null){
                                orderDetail.setQuantity(detailRequestDTO.getQty());
                                orderDetail.setOrderPrice(combination.get().getRetailPrice() * detailRequestDTO.getQty());
                            }
                        }
                    }
                    if (!cartUpdateRequestDTO.getRemovedCombinationList().isEmpty()){
                        for (long combinationId :
                                cartUpdateRequestDTO.getRemovedCombinationList()) {
                            Optional<CombinationEntity> combination = combinationRepository.findById(combinationId);
                            OrderDetailEntity orderDetail = orderDetailRepository.findByOrderEntityAndCombinationEntity(order.get(), combination.get());
                            if (orderDetail != null){
                                orderDetailRepository.delete(orderDetail);
                            }
                        }
                    }
                    return new ResponseEntity<>("Cart updated",HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("Cart not found",HttpStatus.BAD_REQUEST);
                }
            }else {
                return new ResponseEntity<>("Cart update detail not found",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    private OrderDetailEntity setValuesToOrderDetail(OrderEntity order,
                                                     CartRequestDTO cartRequestDTO,
                                                     CombinationEntity combinationEntity) {
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        orderDetailEntity.setQuantity(cartRequestDTO.getQty());
        orderDetailEntity.setOrderPrice(combinationEntity.getRetailPrice() * cartRequestDTO.getQty());
        orderDetailEntity.setCombinationEntity(combinationEntity);
        orderDetailEntity.setOrderEntity(order);

        return orderDetailEntity;
    }

    private List<DeliveryDTO> setDeliveryEntityToDto(List<DeliveryEntity> deliveryEntityList) {
        List<DeliveryDTO> list = new ArrayList<>();
        for (DeliveryEntity deliveryEntity :
                deliveryEntityList) {
            list.add(new DeliveryDTO(
                    deliveryEntity.getDeliveryId(),
                    deliveryEntity.getName(),
                    costEntityToDto(deliveryEntity.getCostEntityList())
            ));
        }
        return list;
    }

    private List<DeliveryCostDTO> costEntityToDto(List<DeliveryCostEntity> costEntityList) {
        List<DeliveryCostDTO> costList = new ArrayList<>();
        for (DeliveryCostEntity deliveryCostEntity :
                costEntityList) {
            costList.add(new DeliveryCostDTO(
                    deliveryCostEntity.getDeliveryCostId(),
                    deliveryCostEntity.getDistrict(),
                    deliveryCostEntity.getCost(),
                    deliveryCostEntity.getCostPerExtra()
            ));
        }
        return costList;
    }

    private SingleProductResponseDTO setProductDetails(OrderDetailEntity detailEntity) {
        CombinationEntity combinationEntity = detailEntity.getCombinationEntity();
        ProductEntity productEntity = getProductFromCombination(combinationEntity);
        if (productEntity != null) {
            return new SingleProductResponseDTO(
                    productEntity.getUuid(),
                    productEntity.getProductCode(),
                    productEntity.getName(),
                    productEntity.getDescription(),
                    productEntity.getBrand(),
                    productEntity.isFreeShipping(),
                    new CombinationDTO(
                            combinationEntity.getCombinationId(),
                            combinationEntity.getStock(),
                            combinationEntity.getWeight(),
                            combinationEntity.getSalePrice(),
                            combinationEntity.getRetailPrice(),
                            setVariation(combinationEntity)
                    )
            );
        } else {
            return null;
        }
    }

    private List<VariationDTO> setVariation(CombinationEntity combinationEntity) {
        List<VariationCombinationDetailEntity> combinationDetail = variationCombinationDetailRepository.findByCombinationEntity(combinationEntity);
        List<VariationDTO> variationDTOList = new ArrayList<>();
        for (VariationCombinationDetailEntity combinationDetailEntity :
                combinationDetail) {
            VariationDTO variationDTO = new VariationDTO(
                    combinationDetailEntity.getVariationDetailEntity().getVariationEntity().getVariationId(),
                    combinationDetailEntity.getVariationDetailEntity().getVariationEntity().getVariationName(),
                    combinationDetailEntity.getVariationDetailEntity().getVariationDetailId(),
                    combinationDetailEntity.getVariationDetailEntity().getValue()
            );
            variationDTOList.add(variationDTO);
        }
        return variationDTOList;
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

    private CartResponseDTO setCart(List<OrderDetailEntity> orderDetailList){
        List<SingleProductResponseDTO> productList = new ArrayList<>();
        double totalWeight = 0;
        double totalProductPrice = 0;
        for (OrderDetailEntity orderDetailEntity :
                orderDetailList) {
            SingleProductResponseDTO productResponseDTO = setProductDetails(orderDetailEntity);
            totalWeight += productResponseDTO.getCombinationDTO().getWeight();
            totalProductPrice += productResponseDTO.getCombinationDTO().getRetailPrice();

            productList.add(productResponseDTO);
        }
        return new CartResponseDTO(
                productList,
                totalWeight,
                totalProductPrice
        );
    }
}
