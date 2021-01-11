package lk.robot.newgenic.controller;

import lk.robot.newgenic.entity.SubCategoryEntity;
import lk.robot.newgenic.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sub_category")
@CrossOrigin
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("get_all")
    public List<SubCategoryEntity> getAll(){
        return subCategoryService.getAll();
    }

}
