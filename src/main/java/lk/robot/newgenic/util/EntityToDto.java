package lk.robot.newgenic.util;

import lk.robot.newgenic.dto.ProductDTO;
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
                productEntity.getBuyingPrice(),
                productEntity.getSalePrice(),
                productEntity.getRetailPrice(),
                productEntity.getAddedDate(),
                productEntity.isActive()
        );
    }

    public static UserFeedbackDTO userEntityToDto(UserEntity userEntity){
        return new UserFeedbackDTO(
                userEntity.getUserId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getProfilePicture()
        );
    }
}
