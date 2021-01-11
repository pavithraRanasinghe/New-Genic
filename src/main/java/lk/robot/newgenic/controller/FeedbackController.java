package lk.robot.newgenic.controller;

import lk.robot.newgenic.dto.Request.FeedbackRequestDTO;
import lk.robot.newgenic.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/feedback")
@CrossOrigin
public class FeedbackController {

    private FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("view/{productId}")
    public ResponseEntity<?> getFeedback(@PathVariable long productId){
        return feedbackService.getFeedback(productId);
    }

    @PostMapping
    public ResponseEntity<?> writeFeedback(@RequestBody FeedbackRequestDTO feedbackRequestDTO, Principal principal){
        long userId = Long.parseLong(principal.getName());
        return feedbackService.writeFeedback(feedbackRequestDTO, userId);
    }

}
