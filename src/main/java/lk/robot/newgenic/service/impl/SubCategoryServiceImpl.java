package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.entity.SubCategoryEntity;
import lk.robot.newgenic.repository.SubCategoryRepository;
import lk.robot.newgenic.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public List<SubCategoryEntity> getAll() {
        return subCategoryRepository.findAll();
    }
}
