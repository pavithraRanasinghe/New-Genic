package lk.robot.newgenic.controller.user;

import lk.robot.newgenic.dto.Request.FeedbackRequestDTO;
import lk.robot.newgenic.service.user.FeedbackService;
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

    @GetMapping("/{productId}")
    public ResponseEntity<?> getFeedback(@PathVariable long productId,@RequestParam int index,@RequestParam int size){
        return feedbackService.getFeedback(productId,index,size);
    }

    @PostMapping
    public ResponseEntity<?> writeFeedback(@RequestBody FeedbackRequestDTO feedbackRequestDTO, Principal principal){
        long userId = Long.parseLong(principal.getName());
        return feedbackService.writeFeedback(feedbackRequestDTO, userId);
    }

}
