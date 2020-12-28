package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryResponseDTO> getAll();
}
