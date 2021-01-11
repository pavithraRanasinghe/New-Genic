package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.Request.FeedbackRequestDTO;
import org.springframework.http.ResponseEntity;

public interface FeedbackService {

    ResponseEntity<?> getFeedback(long productId);

    ResponseEntity<?> writeFeedback(FeedbackRequestDTO feedbackRequestDTO,long userId);
}
