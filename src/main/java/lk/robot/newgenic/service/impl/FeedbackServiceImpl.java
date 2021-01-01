package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.response.ProductFeedbackResponseDTO;
import lk.robot.newgenic.dto.response.UserFeedbackDTO;
import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.entity.ProductFeedbackEntity;
import lk.robot.newgenic.entity.UserEntity;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.FeedbackRepository;
import lk.robot.newgenic.repository.ProductRepository;
import lk.robot.newgenic.service.FeedbackService;
import lk.robot.newgenic.util.EntityToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackRepository feedbackRepository;
    private ProductRepository productRepository;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, ProductRepository productRepository) {
        this.feedbackRepository = feedbackRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<?> getFeedback(long productId) {
        try{
            Optional<ProductEntity> productEntity = productRepository.findById(productId);
            if(productEntity.isPresent()){
                List<ProductFeedbackEntity> productFeedbackList = feedbackRepository.findByProductEntity(productEntity.get());
                if (!productFeedbackList.isEmpty()){
                    List<ProductFeedbackResponseDTO> feedbackList = new ArrayList<>();
                    for (ProductFeedbackEntity feedbackEntity:productFeedbackList) {

                        ProductFeedbackResponseDTO productFeedbackResponseDTO = new ProductFeedbackResponseDTO(
                                feedbackEntity.getProductFeedbackId(),
                                feedbackEntity.getRate(),
                                feedbackEntity.getMessage(),
                                feedbackEntity.getAddedDate(),
                                EntityToDto.userEntityToDto(feedbackEntity.getUserEntity())
                        );

                        feedbackList.add(productFeedbackResponseDTO);
                    }
                    return new ResponseEntity<>(feedbackList,HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Feedback not found",HttpStatus.NOT_FOUND);
                }
            }else{
                return new ResponseEntity<>("No product found", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new CustomException("Feedback fetching failed");
        }
    }


}
