package lk.robot.newgenic.util;

import lk.robot.newgenic.dto.ProductDTO;
import lk.robot.newgenic.dto.Request.UserSignUpDTO;
import lk.robot.newgenic.dto.response.DeliveryCostDTO;
import lk.robot.newgenic.dto.response.UserFeedbackDTO;
import lk.robot.newgenic.entity.DeliveryCostEntity;
import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.entity.UserEntity;

public class EntityToDto {

    public static ProductDTO productEntityToDto(ProductEntity productEntity){
        return new ProductDTO(
                productEntity.getProductId(),
                productEntity.getProductCode(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getStock(),
                productEntity.getColor(),
                productEntity.getSize(),
                productEntity.getGender(),
                productEntity.getWeight(),
                productEntity.getBuyingPrice(),
                productEntity.getSalePrice(),
                productEntity.getRetailPrice(),
                productEntity.getAddedDate(),
                productEntity.isActive(),
                productEntity.isFreeShipping(),
                productEntity.getDealEntity().getDiscount()
        );
    }

    public static UserFeedbackDTO userEntityToUserFeedbackDto(UserEntity userEntity){
        return new UserFeedbackDTO(
                userEntity.getUserId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getProfilePicture()
        );
    }

    public static UserEntity userDtoToEntity(UserSignUpDTO userSignUpDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userSignUpDTO.getFirstName());
        userEntity.setLastName(userSignUpDTO.getLastName());
        userEntity.setUsername(userSignUpDTO.getUserName());
        userEntity.setGmail(userSignUpDTO.getGmail());
        userEntity.setMobile(userSignUpDTO.getMobile());
        userEntity.setDob(DateConverter.stringToDate(userSignUpDTO.getDob()));
        userEntity.setPassword(userSignUpDTO.getPassword());
        userEntity.setProfilePicture(userSignUpDTO.getProfilePicture());
        userEntity.setRole(userSignUpDTO.getRole());

        return userEntity;

    }

    public static DeliveryCostDTO deliveryCostEntityToDTO(DeliveryCostEntity deliveryCostEntity){
        return new DeliveryCostDTO(
                deliveryCostEntity.getDeliveryCostId(),
                deliveryCostEntity.getGalle(),
                deliveryCostEntity.getMatara(),
                deliveryCostEntity.getHambantota(),
                deliveryCostEntity.getKaluthra(),
                deliveryCostEntity.getColombo(),
                deliveryCostEntity.getGampaha(),
                deliveryCostEntity.getPuttalm(),
                deliveryCostEntity.getKurunegala(),
                deliveryCostEntity.getJaffna(),
                deliveryCostEntity.getKilinochchi(),
                deliveryCostEntity.getMannar(),
                deliveryCostEntity.getMullaitivu(),
                deliveryCostEntity.getVavuniya(),
                deliveryCostEntity.getAnuradhapura(),
                deliveryCostEntity.getPolonnaruwa(),
                deliveryCostEntity.getMatale(),
                deliveryCostEntity.getKandy(),
                deliveryCostEntity.getNuwaraEliya(),
                deliveryCostEntity.getRatnapura(),
                deliveryCostEntity.getKegalle(),
                deliveryCostEntity.getTrincomalee(),
                deliveryCostEntity.getBatticaloa(),
                deliveryCostEntity.getAmpara(),
                deliveryCostEntity.getMonaragala(),
                deliveryCostEntity.getBadulla(),
                deliveryCostEntity.getCostPerExtra()
        );
    }
}
