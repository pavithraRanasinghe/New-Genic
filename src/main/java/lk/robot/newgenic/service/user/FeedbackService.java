package lk.robot.newgenic.service.user;

import lk.robot.newgenic.dto.Request.FeedbackRequestDTO;
import org.springframework.http.ResponseEntity;

public interface FeedbackService {

    ResponseEntity<?> getFeedback(long productId,int index,int size);

    ResponseEntity<?> writeFeedback(FeedbackRequestDTO feedbackRequestDTO,long userId);
}
