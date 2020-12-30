package lk.robot.newgenic.service;

import org.springframework.http.ResponseEntity;

public interface FeedbackService {

    ResponseEntity<?> getFeedback(long productId);
}
