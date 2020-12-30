package lk.robot.newgenic.service;

import lk.robot.newgenic.entity.ProductEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<?> newArrivals();

    ResponseEntity<?> fantechProduct();
}
