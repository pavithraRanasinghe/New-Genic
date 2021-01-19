package lk.robot.newgenic.service.user.impl;

import lk.robot.newgenic.dto.user.Request.FeedbackRequestDTO;
import lk.robot.newgenic.dto.user.response.ProductFeedbackResponseDTO;
import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.entity.ProductFeedbackEntity;
import lk.robot.newgenic.entity.UserEntity;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.user.FeedbackRepository;
import lk.robot.newgenic.repository.user.ProductRepository;
import lk.robot.newgenic.repository.user.UserRepository;
import lk.robot.newgenic.service.user.FeedbackService;
import lk.robot.newgenic.util.DateConverter;
import lk.robot.newgenic.util.EntityToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackRepository feedbackRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository,
                               ProductRepository productRepository,
                               UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> getFeedback(long productId, int index, int size) {
        try {
            Optional<ProductEntity> productEntity = productRepository.findById(productId);
            if (productEntity.isPresent()) {
                List<ProductFeedbackEntity> productFeedbackList = feedbackRepository.findByProductEntityAndApproved(productEntity.get(), true, PageRequest.of(index, size));
                if (!productFeedbackList.isEmpty()) {
                    List<ProductFeedbackResponseDTO> feedbackList = new ArrayList<>();
                    for (ProductFeedbackEntity feedbackEntity : productFeedbackList) {

                        ProductFeedbackResponseDTO productFeedbackResponseDTO = new ProductFeedbackResponseDTO(
                                feedbackEntity.getProductFeedbackId(),
                                feedbackEntity.getRate(),
                                feedbackEntity.getMessage(),
                                feedbackEntity.getAddedDate(),
                                EntityToDto.userEntityToUserFeedbackDto(feedbackEntity.getUserEntity())
                        );

                        feedbackList.add(productFeedbackResponseDTO);
                    }
                    return new ResponseEntity<>(feedbackList, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Feedback not found", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("No product found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException("Feedback fetching failed");
        }
    }

    @Override
    public ResponseEntity<?> writeFeedback(FeedbackRequestDTO feedbackRequestDTO, long userId) {
        try {
            if (feedbackRequestDTO != null) {
                Optional<UserEntity> userEntity = userRepository.findById(userId);
                Optional<ProductEntity> productEntity = productRepository.findById(feedbackRequestDTO.getProductId());
                if (productEntity.isPresent()) {
                    ProductFeedbackEntity feedbackEntity = new ProductFeedbackEntity();
                    feedbackEntity.setRate(feedbackRequestDTO.getRate());
                    feedbackEntity.setMessage(feedbackRequestDTO.getMessage());
                    feedbackEntity.setAddedDate(DateConverter.localDateToSql(LocalDate.now()));
                    feedbackEntity.setAddedTime(DateConverter.localTimeToSql(LocalTime.now()));
                    feedbackEntity.setUserEntity(userEntity.get());
                    feedbackEntity.setProductEntity(productEntity.get());

                    ProductFeedbackEntity save = feedbackRepository.save(feedbackEntity);
                    if (save != null) {
                        return new ResponseEntity<>("Feedback successful", HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Feedback unsuccessful", HttpStatus.EXPECTATION_FAILED);
                    }
                } else {
                    return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Feedback details not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new CustomException("Failed to write feedback");
        }
    }


}
