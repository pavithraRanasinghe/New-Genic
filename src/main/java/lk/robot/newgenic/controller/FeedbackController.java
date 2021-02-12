package lk.robot.newgenic.controller;

import lk.robot.newgenic.dto.request.FeedbackRequestDTO;
import lk.robot.newgenic.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/feedback")
@CrossOrigin
public class FeedbackController {

    private FeedbackService feedbackService;
    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class);

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getFeedback(@PathVariable long productId,
                                         @RequestParam int index,
                                         @RequestParam int size){
        LOGGER.info("request - allUser | getFeedback | productId: {} | index: {} | size: {}",productId,index,size);
        ResponseEntity<?> feedback = feedbackService.getFeedback(productId, index, size);
        LOGGER.info("response - allUser | getFeedback | feedback: {}",feedback.getStatusCode());
        return feedback;
    }

    @PostMapping
    public ResponseEntity<?> writeFeedback(@RequestBody FeedbackRequestDTO feedbackRequestDTO,
                                           Principal principal){
        LOGGER.info("request - registeredUser | writeFeedback | feedbackRequest: {} | userId: {} | size: {}",feedbackRequestDTO,principal.getName());
        long userId = Long.parseLong(principal.getName());
        ResponseEntity<?> feedbackResponse = feedbackService.writeFeedback(feedbackRequestDTO, userId);
        LOGGER.info("response - registeredUser | writeFeedback | feedback: {}",feedbackResponse.getStatusCode());
        return feedbackResponse;
    }

}
