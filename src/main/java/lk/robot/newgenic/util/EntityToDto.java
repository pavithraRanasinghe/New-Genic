package lk.robot.newgenic.util;

import lk.robot.newgenic.dto.ProductDTO;
import lk.robot.newgenic.dto.request.UserSignUpDTO;
import lk.robot.newgenic.dto.response.UserFeedbackDTO;
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
        userEntity.setRole(userSignUpDTO.getRole());

        return userEntity;

    }
}
