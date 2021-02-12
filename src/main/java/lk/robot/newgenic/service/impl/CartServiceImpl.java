package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.DeliveryDTO;
import lk.robot.newgenic.dto.ProductDTO;
import lk.robot.newgenic.dto.CartProductDTO;
import lk.robot.newgenic.dto.request.CartRequestDTO;
import lk.robot.newgenic.dto.response.CartOrderResponse;
import lk.robot.newgenic.dto.response.CartResponseDTO;
import lk.robot.newgenic.dto.response.DeliveryCostDTO;
import lk.robot.newgenic.entity.*;
import lk.robot.newgenic.enums.DiscountType;
import lk.robot.newgenic.enums.OrderStatus;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.*;
import lk.robot.newgenic.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Autowired
    public CartServiceImpl(
            ProductRepository productRepository,
            OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository,
            UserRepository userRepository,
            DiscountMethodRepository discountMethodRepository,
            DeliveryRepository deliveryRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.userRepository = userRepository;
        this.discountMethodRepository = discountMethodRepository;
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public ResponseEntity<?> addToCart(CartRequestDTO cartRequestDTO, long userId) {
        try {
            if (cartRequestDTO.getProductId() != 0) {
                Optional<ProductEntity> productEntity = productRepository.findById(cartRequestDTO.getProductId());
                Optional<UserEntity> userEntity = userRepository.findById(userId);
                if (productEntity.isPresent() && userEntity.isPresent()) {
                    OrderEntity existCart = orderRepository.findByUserEntityAndStatus(userEntity.get(),
                            OrderStatus.CART.toString());
                    if (existCart != null && existCart.getUserEntity().equals(userEntity.get())) {
                        OrderDetailEntity existProduct = orderDetailRepository.
                                findByOrderEntityAndProductEntity(existCart, productEntity.get());
                        if (existProduct != null) {
                            return new ResponseEntity<>("Product already added to cart", HttpStatus.CONFLICT);
                        }
                        existCart.setTotalWeight(existCart.getTotalWeight() + cartRequestDTO.getWeight());
                        String uuid = UUID.randomUUID().toString();
                        existCart.setOrderUuid(uuid);
                        OrderEntity order = orderRepository.save(existCart);
                        if (!order.equals(null)) {
                            OrderDetailEntity orderDetailEntity = setValuesToOrderDetail(order, cartRequestDTO, productEntity.get());

                            OrderDetailEntity save = orderDetailRepository.save(orderDetailEntity);
                            return new ResponseEntity<>("Product add to cart successful", HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>("Product add to cart failed", HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    } else {
                        OrderEntity orderEntity = new OrderEntity();
                        orderEntity.setStatus(OrderStatus.CART.toString());
                        orderEntity.setTotalWeight(cartRequestDTO.getWeight());
                        orderEntity.setUserEntity(userEntity.get());
                        orderEntity.setOrderUuid(UUID.randomUUID().toString());
                        OrderEntity order = orderRepository.save(orderEntity);
                        if (!order.equals(null)) {
                            OrderDetailEntity orderDetailEntity = setValuesToOrderDetail(order, cartRequestDTO, productEntity.get());
                            OrderDetailEntity save = orderDetailRepository.save(orderDetailEntity);

                            return new ResponseEntity<>("Product add to cart successful", HttpStatus.OK);
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
    public ResponseEntity<?> getCart(long userId) {
        try {
            Optional<UserEntity> userEntity = userRepository.findById(userId);
            OrderEntity orderEntity = orderRepository.
                    findByUserEntityAndStatus(userEntity.get(), OrderStatus.CART.toString());
            if (orderEntity != null) {
                List<OrderDetailEntity> orderDetailList = orderDetailRepository.findByOrderEntity(orderEntity);
                if (!orderDetailList.isEmpty()) {
                    List<CartProductDTO> productList = new ArrayList<>();
                    double totalWeight = 0;
                    double totalProductPrice = 0;
                    for (OrderDetailEntity orderDetailEntity :
                            orderDetailList) {
                        CartProductDTO cartProductDTO = entityToDTO(orderDetailEntity);
                        totalWeight += cartProductDTO.getWeight();
                        totalProductPrice += cartProductDTO.getRetailPrice();

                        productList.add(cartProductDTO);
                    }
                    CartResponseDTO cartResponseDTO = new CartResponseDTO(
                            productList,
                            totalWeight,
                            totalProductPrice
                    );
                    return new ResponseEntity<>(cartResponseDTO, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("No cart for " + userEntity.get().getUsername(), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("No cart for " + userEntity.get().getUsername(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException("Failed to fetch cart");
        }
    }

    @Override
    public ResponseEntity<?> cartOrder(long userId) {
        try {
            ResponseEntity<?> cartResponse = getCart(userId);
            List<ProductDTO> cart = (List<ProductDTO>) cartResponse.getBody();
            List<DeliveryEntity> allDeliveries = deliveryRepository.findAll();
            CartOrderResponse cartOrderResponse = new CartOrderResponse();
            double cartPrice = 0;
            double totalWeight = 0;
            for (ProductDTO productDTO :
                    cart) {
                cartPrice = cartPrice + productDTO.getRetailPrice();
                totalWeight = totalWeight + productDTO.getWeight();
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
            cartOrderResponse.setProductDTOList(cart);
            cartOrderResponse.setCartPrice(cartPrice);
            cartOrderResponse.setTotalWeight(totalWeight);
            return new ResponseEntity<>(cartOrderResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomException("Failed to load cart order");
        }
    }

    private OrderDetailEntity setValuesToOrderDetail(OrderEntity order,
                                                     CartRequestDTO cartRequestDTO,
                                                     ProductEntity productEntity) {
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        orderDetailEntity.setQuantity(cartRequestDTO.getQty());
        orderDetailEntity.setOrderPrice(cartRequestDTO.getPrice() * cartRequestDTO.getQty());
        orderDetailEntity.setProductEntity(productEntity);
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

    private List<DeliveryCostDTO> costEntityToDto(List<DeliveryCostEntity> costEntityList){
        List<DeliveryCostDTO> costList = new ArrayList<>();
        for (DeliveryCostEntity deliveryCostEntity:
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

    private CartProductDTO entityToDTO(OrderDetailEntity detailEntity){
        return new CartProductDTO(
                detailEntity.getProductEntity().getProductId(),
                detailEntity.getProductEntity().getName(),
                detailEntity.getProductEntity().getProductCode(),
                detailEntity.getProductEntity().getDescription(),
                detailEntity.getProductEntity().getColor(),
                detailEntity.getProductEntity().getWeight(),
                detailEntity.getProductEntity().getSalePrice(),
                detailEntity.getProductEntity().getRetailPrice(),
                detailEntity.getProductEntity().isFreeShipping(),
                detailEntity.getQuantity(),
                detailEntity.getQuantity() * detailEntity.getProductEntity().getRetailPrice()
        );
    }
}
