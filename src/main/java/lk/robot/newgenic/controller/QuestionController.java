package lk.robot.newgenic.controller;

import lk.robot.newgenic.dto.request.QuestionRequestDTO;
import lk.robot.newgenic.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/question")
@CrossOrigin
public class QuestionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);
    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/ask")
    public ResponseEntity<?> askQuestion(@RequestBody QuestionRequestDTO questionRequestDTO, Principal principal){
        LOGGER.info("request - user | askQuestion | questionRequest: {} | userId: {}",questionRequestDTO, principal.getName());
        ResponseEntity<?> questionResponse = questionService.askQuestion(questionRequestDTO, principal.getName());
        LOGGER.info("response - user | askQuestion | questionResponse: {}",questionResponse);
        return questionResponse;
    }

    @GetMapping("/getQuestion/{productId}")
    public ResponseEntity<?> getQuestion(@PathVariable String productId){
        LOGGER.info("request - user | askQuestion | productId: {}",productId);
        ResponseEntity question = questionService.getQuestion(productId);
        LOGGER.info("response - user | askQuestion | questionResponse: {}",question);
        return question;
    }
}
