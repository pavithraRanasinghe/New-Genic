package lk.robot.newgenic.service.admin.impl;

import lk.robot.newgenic.dto.admin.SubCategoryDTO;
import lk.robot.newgenic.dto.admin.request.CategoryRequestDTO;
import lk.robot.newgenic.entity.AdminEntity;
import lk.robot.newgenic.entity.MainCategoryEntity;
import lk.robot.newgenic.entity.MainSubCategoryEntity;
import lk.robot.newgenic.entity.SubCategoryEntity;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.admin.AdminRepository;
import lk.robot.newgenic.repository.admin.MainCategoryRepository;
import lk.robot.newgenic.repository.admin.MainSubCategoryRepository;
import lk.robot.newgenic.repository.user.SubCategoryRepository;
import lk.robot.newgenic.service.admin.AdminCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {

    private MainCategoryRepository mainCategoryRepository;
    private AdminRepository adminRepository;
    private MainSubCategoryRepository mainSubCategoryRepository;
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    public AdminCategoryServiceImpl(MainCategoryRepository mainCategoryRepository,
                                    AdminRepository adminRepository,
                                    MainSubCategoryRepository mainSubCategoryRepository,
                                    SubCategoryRepository subCategoryRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
        this.adminRepository = adminRepository;
        this.mainSubCategoryRepository = mainSubCategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public ResponseEntity<?> addCategory(CategoryRequestDTO categoryRequestDTO, long adminId) {
        try {
            if (categoryRequestDTO != null && !categoryRequestDTO.getSubCategoryList().isEmpty()) {
                Optional<AdminEntity> admin = adminRepository.findById(adminId);
                Optional<MainCategoryEntity> mainCategory = mainCategoryRepository.findById(categoryRequestDTO.getMainCategoryId());
                if (mainCategory.isPresent()) {
                    MainSubCategoryEntity existingMainSub = mainSubCategoryRepository.findByMainSubCategoryNameAndMainCategoryEntity(
                            categoryRequestDTO.getMainSubCategoryName(), mainCategory.get());
                    if (!existingMainSub.equals(null)) {
                        MainSubCategoryEntity mainSubCategoryEntity = new MainSubCategoryEntity();
                        mainSubCategoryEntity.setMainSubCategoryName(categoryRequestDTO.getMainSubCategoryName());
                        mainSubCategoryEntity.setMainSubCategoryDescription(categoryRequestDTO.getMainSubCategoryDescription());
                        mainSubCategoryEntity.setMainCategoryEntity(mainCategory.get());

                        MainSubCategoryEntity mainSubCategory = mainSubCategoryRepository.save(mainSubCategoryEntity);
                        if (mainSubCategory != null) {
                            List<SubCategoryDTO> existingSubCategories = saveSubCategories(categoryRequestDTO, mainSubCategory);
                            if (existingSubCategories.isEmpty()) {
                                return new ResponseEntity<>("All categories added", HttpStatus.OK);
                            } else {
                                return new ResponseEntity<>(existingSubCategories, HttpStatus.OK);
                            }

                        } else {
                            return new ResponseEntity<>("Something wrong on main sub category", HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        List<SubCategoryDTO> existingSubCategories = saveSubCategories(categoryRequestDTO, existingMainSub);
                        if (existingSubCategories.isEmpty()) {
                            return new ResponseEntity<>("All categories added", HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(existingSubCategories, HttpStatus.OK);
                        }
                    }
                } else {
                    return new ResponseEntity<>("Main category not found", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Category details not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    private SubCategoryEntity subCategoryDtoToEntity(SubCategoryDTO subCategoryDTO, MainSubCategoryEntity mainSubCategoryEntity) {
        SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
        subCategoryEntity.setSubCategoryName(subCategoryDTO.getSubCategoryName());
        subCategoryEntity.setSubCategoryDescription(subCategoryDTO.getSubCategoryDescription());
        subCategoryEntity.setMainSubCategory(mainSubCategoryEntity);

        return subCategoryEntity;
    }

    private List<SubCategoryDTO> saveSubCategories(CategoryRequestDTO categoryRequestDTO, MainSubCategoryEntity mainSubCategoryEntity) {
        List<SubCategoryDTO> existingSubCategories = null;
        for (SubCategoryDTO subCategoryDTO :
                categoryRequestDTO.getSubCategoryList()) {
            SubCategoryEntity existingSubCategory = subCategoryRepository.
                    findBySubCategoryNameAndMainSubCategoryEntity(subCategoryDTO.getSubCategoryName(), mainSubCategoryEntity);
            if (existingSubCategory.equals(null)) {
                SubCategoryEntity subCategoryEntity = subCategoryDtoToEntity(subCategoryDTO, mainSubCategoryEntity);
                subCategoryRepository.save(subCategoryEntity);
            } else {
                existingSubCategories.add(subCategoryDTO);
            }
        }
        return existingSubCategories;
    }
}
