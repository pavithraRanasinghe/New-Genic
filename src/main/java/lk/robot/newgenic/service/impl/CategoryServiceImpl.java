package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.ProductDTO;
import lk.robot.newgenic.dto.SubCategoryDTO;
import lk.robot.newgenic.dto.response.CategoryResponseDTO;
import lk.robot.newgenic.dto.response.MainSubCategoryResponseDTO;
import lk.robot.newgenic.entity.MainCategoryEntity;
import lk.robot.newgenic.entity.MainSubCategoryEntity;
import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.entity.SubCategoryEntity;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.CategoryRepository;
import lk.robot.newgenic.repository.MainSubCategoryRepository;
import lk.robot.newgenic.repository.ProductRepository;
import lk.robot.newgenic.repository.SubCategoryRepository;
import lk.robot.newgenic.service.CategoryService;
import lk.robot.newgenic.util.EntityToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private MainSubCategoryRepository mainSubCategoryRepository;
    private SubCategoryRepository subCategoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public CategoryServiceImpl(
            CategoryRepository categoryRepository,
            MainSubCategoryRepository mainSubCategoryRepository,
            SubCategoryRepository subCategoryRepository,
            ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.mainSubCategoryRepository = mainSubCategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<?> getAll() {

        List<CategoryResponseDTO> categoryResponseDTOList = new ArrayList<>();
        try{
            List<MainCategoryEntity> all = categoryRepository.findAll();
            if (all.size() != 0){
                for (MainCategoryEntity mainCategoryEntity:all) {

                    CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
                    categoryResponseDTO.setMain_category_id(mainCategoryEntity.getMainCategoryId());
                    categoryResponseDTO.setMain_category_name(mainCategoryEntity.getMainCategoryName());
                    categoryResponseDTO.setMain_category_description(mainCategoryEntity.getMainCategoryDescription());

                    List<MainSubCategoryEntity> mainSubCategoryList = mainSubCategoryRepository.findByMainCategoryEntity(mainCategoryEntity);
                    List<MainSubCategoryResponseDTO> mainSubCategoryResponseDTOList = new ArrayList<>();
                    if (mainSubCategoryList.size() != 0){
                        for (MainSubCategoryEntity mainSubCategoryEntity:mainSubCategoryList) {

                            MainSubCategoryResponseDTO mainSubCategoryResponseDTO = new MainSubCategoryResponseDTO();
                            mainSubCategoryResponseDTO.setMain_sub_category_id(mainSubCategoryEntity.getMainSubCategoryId());
                            mainSubCategoryResponseDTO.setMain_sub_category_name(mainSubCategoryEntity.getMainSubCategoryName());
                            mainSubCategoryResponseDTO.setMain_sub_category_description(mainSubCategoryEntity.getMainSubCategoryDescription());

                            List<SubCategoryEntity> subCategoryList = subCategoryRepository.findByMainSubCategoryEntity
                                    (mainSubCategoryEntity);
                            if (subCategoryList.size() != 0){
                                List<SubCategoryDTO> subCategoryDTOList = new ArrayList<>();
                                for (SubCategoryEntity subCategoryEntity:subCategoryList) {
                                    SubCategoryDTO subCategoryDTO = new SubCategoryDTO(
                                            subCategoryEntity.getSubCategoryId(),
                                            subCategoryEntity.getSubCategoryName(),
                                            subCategoryEntity.getSubCategoryDescription()
                                    );
                                    subCategoryDTOList.add(subCategoryDTO);
                                    mainSubCategoryResponseDTO.setSubCategoryDTO(subCategoryDTOList);
                                }
                            }else{
                                return new ResponseEntity<>("Not found Sub category list", HttpStatus.NOT_FOUND);
                            }

                            mainSubCategoryResponseDTOList.add(mainSubCategoryResponseDTO);
                        }
                    }else{
                        return new ResponseEntity<>("Not found Main sub category list", HttpStatus.NOT_FOUND);
                    }
                    categoryResponseDTO.setMainSubCategoryResponseDTO(mainSubCategoryResponseDTOList);
                    categoryResponseDTOList.add(categoryResponseDTO);
                }
                return new ResponseEntity<>(categoryResponseDTOList,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Not found Main category list", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            throw new CustomException("Category search failed");
        }
    }

    @Override
    public ResponseEntity<?> getMainSubCategoryProducts(long mainSubCategoryId) {
        try{
            if(mainSubCategoryId != 0){
                Optional<MainSubCategoryEntity> mainSubCategory = mainSubCategoryRepository.findById(mainSubCategoryId);
                if (mainSubCategory.isPresent()){
                    List<SubCategoryEntity> subCategoryList = subCategoryRepository.findByMainSubCategoryEntity(mainSubCategory.get());
                    if (!subCategoryList.isEmpty()){
                        List<ProductDTO> productList = new ArrayList<>();
                        for (SubCategoryEntity subCategoryEntity:subCategoryList) {
                            List<ProductEntity> productEntityList = productRepository.findBySubCategoryEntityAndActive(subCategoryEntity,true);
                            if (!productEntityList.isEmpty()){
                                for (ProductEntity productEntity:productEntityList) {
                                    ProductDTO productDTO = EntityToDto.productEntityToDto(productEntity);
                                    productList.add(productDTO);
                                }
                            }else{
                                return new ResponseEntity<>("Product list not found",HttpStatus.NOT_FOUND);
                            }
                        }
                        return new ResponseEntity<>(productList, HttpStatus.OK);
                    }else{
                        return new ResponseEntity<>("Sub category list not found",HttpStatus.NOT_FOUND);
                    }
                }else {
                    return new ResponseEntity<>("Main sub category not found",HttpStatus.NOT_FOUND);
                }
            }else {
                return new ResponseEntity<>("Main sub category id required",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new CustomException("Something went wrong in main sub category product search");
        }
    }

    @Override
    public ResponseEntity<?> getSubCategoryProducts(long subCategoryId) {
        if (subCategoryId != 0){
            Optional<SubCategoryEntity> subCategoryEntity = subCategoryRepository.findById(subCategoryId);
            if (subCategoryEntity.isPresent()){
                List<ProductEntity> productEntityList = productRepository.findBySubCategoryEntityAndActive(subCategoryEntity.get(),true);
                if (!productEntityList.isEmpty()){
                    List<ProductDTO> productList = new ArrayList<>();
                    for (ProductEntity productEntity:productEntityList) {
                        ProductDTO productDTO = EntityToDto.productEntityToDto(productEntity);
                        productList.add(productDTO);
                    }
                    return new ResponseEntity<>(productList,HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("Products not found",HttpStatus.NOT_FOUND);
                }
            }else {
                return new ResponseEntity<>("Sub category not found",HttpStatus.NOT_FOUND);
            }
        }else {
            return new ResponseEntity<>("Sub category id required",HttpStatus.BAD_REQUEST);
        }
    }
}
