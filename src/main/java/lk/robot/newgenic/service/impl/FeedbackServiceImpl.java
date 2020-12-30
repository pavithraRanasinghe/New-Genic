package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.repository.FeedbackRepository;
import lk.robot.newgenic.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public ResponseEntity<?> getFeedback(long productId) {
        // TODO: 31/12/2020 Complete View Feedback API
        return null;
    }
}
