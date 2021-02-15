package lk.robot.newgenic.controller;

import lk.robot.newgenic.dto.request.QuestionRequestDTO;
import lk.robot.newgenic.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);
    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/ask")
    public ResponseEntity<?> askQuestion(@RequestBody QuestionRequestDTO questionRequestDTO, Principal principal){
        LOGGER.info("request - admin | askQuestion | questionRequest: {} | userId: {}",questionRequestDTO, principal.getName());
        ResponseEntity<?> questionResponse = questionService.askQuestion(questionRequestDTO, principal.getName());
        LOGGER.info("response - admin | askQuestion | questionResponse: {}",questionResponse);
        return questionResponse;
    }
}
