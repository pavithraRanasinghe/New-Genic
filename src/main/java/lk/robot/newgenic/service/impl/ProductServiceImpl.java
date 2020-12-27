package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.repository.ProductRepository;
import lk.robot.newgenic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductEntity>  getAll() {
        return productRepository.findAll();
    }
}
