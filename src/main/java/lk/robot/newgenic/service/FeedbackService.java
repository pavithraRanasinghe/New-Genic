package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.request.FeedbackRequestDTO;
import org.springframework.http.ResponseEntity;

public interface FeedbackService {

    ResponseEntity<?> getFeedback(String productId,int index,int size);

    ResponseEntity<?> writeFeedback(FeedbackRequestDTO feedbackRequestDTO,String userId);
}
