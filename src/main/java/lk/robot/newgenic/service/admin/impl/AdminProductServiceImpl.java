package lk.robot.newgenic.service.admin.impl;

import lk.robot.newgenic.dto.admin.request.ProductRequestDTO;
import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.entity.SubCategoryEntity;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.user.ProductRepository;
import lk.robot.newgenic.repository.user.SubCategoryRepository;
import lk.robot.newgenic.service.admin.AdminProductService;
import lk.robot.newgenic.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminProductServiceImpl implements AdminProductService {

    private ProductRepository productRepository;
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    public AdminProductServiceImpl(ProductRepository productRepository,
                                   SubCategoryRepository subCategoryRepository) {
        this.productRepository = productRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public ResponseEntity<?> addProduct(List<ProductRequestDTO> productRequestList) {
        try{
            if (!productRequestList.isEmpty()){
                Optional<SubCategoryEntity> subCategoryEntity = subCategoryRepository.findById(productRequestList.get(1).getSubCategoryId());
                if (subCategoryEntity.isPresent()){
                    for (ProductRequestDTO productRequestDTO :
                            productRequestList) {
                        ProductEntity productEntity = requestDTOtoEntity(productRequestDTO, subCategoryEntity.get());
                        ProductEntity save = productRepository.save(productEntity);
                    }
                    return new ResponseEntity<>("Product save successful",HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("Sub category not found",HttpStatus.NOT_FOUND);
                }
            }else {
                return new ResponseEntity<>("Product details not found", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    private ProductEntity requestDTOtoEntity(ProductRequestDTO productRequestDTO,SubCategoryEntity subCategoryEntity){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductCode(productRequestDTO.getProductCode());
        productEntity.setName(productRequestDTO.getName());
        productEntity.setDescription(productRequestDTO.getDescription());
        productEntity.setBrand(productRequestDTO.getBrand());
        productEntity.setStock(productRequestDTO.getStock());
        productEntity.setColor(productRequestDTO.getColor());
        productEntity.setSize(productRequestDTO.getSize());
        productEntity.setGender(productRequestDTO.getGender());
        productEntity.setWeight(productRequestDTO.getWeight());
        productEntity.setBuyingPrice(productRequestDTO.getBuyingPrice());
        productEntity.setSalePrice(productRequestDTO.getSalePrice());
        productEntity.setRetailPrice(productRequestDTO.getRetailPrice());
        productEntity.setAddedDate(DateConverter.localDateToSql(LocalDate.now()));
        productEntity.setAddedTime(DateConverter.localTimeToSql(LocalTime.now()));
        productEntity.setActive(productRequestDTO.isActive());
        productEntity.setFreeShipping(productRequestDTO.isFreeShipping());
        productEntity.setSubCategoryEntity(subCategoryEntity);

        return productEntity;
    }
}
