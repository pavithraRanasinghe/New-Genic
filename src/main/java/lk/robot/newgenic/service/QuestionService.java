package lk.robot.newgenic.service;

import lk.robot.newgenic.dto.request.QuestionRequestDTO;
import org.springframework.http.ResponseEntity;

public interface QuestionService {

    ResponseEntity<?> askQuestion(QuestionRequestDTO questionRequestDTO,String userId);

    ResponseEntity<?> getQuestion(String productId);
}
