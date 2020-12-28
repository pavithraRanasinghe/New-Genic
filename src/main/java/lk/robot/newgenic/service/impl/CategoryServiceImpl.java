package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.response.CategoryResponseDTO;
import lk.robot.newgenic.entity.MainCategoryEntity;
import lk.robot.newgenic.repository.CategoryRepository;
import lk.robot.newgenic.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDTO> getAll() {
        List<MainCategoryEntity> all = categoryRepository.findAll();
        if (all != null){
            return null;
        }
        return null;
    }
}
