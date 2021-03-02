package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.AnswerDTO;
import lk.robot.newgenic.dto.QuestionDTO;
import lk.robot.newgenic.dto.request.QuestionRequestDTO;
import lk.robot.newgenic.dto.response.QuestionResponseDTO;
import lk.robot.newgenic.entity.*;
import lk.robot.newgenic.exception.CustomException;
import lk.robot.newgenic.repository.ProductRepository;
import lk.robot.newgenic.repository.QuestionAreaRepository;
import lk.robot.newgenic.repository.QuestionRepository;
import lk.robot.newgenic.repository.UserRepository;
import lk.robot.newgenic.service.QuestionService;
import lk.robot.newgenic.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionAreaRepository questionAreaRepository;
    private QuestionRepository questionRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;


    @Autowired
    public QuestionServiceImpl(QuestionAreaRepository questionAreaRepository,
                               QuestionRepository questionRepository,
                               UserRepository userRepository,
                               ProductRepository productRepository) {
        this.questionAreaRepository = questionAreaRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<?> askQuestion(QuestionRequestDTO questionRequestDTO, String userId) {
        try{
            if (questionRequestDTO != null){
                Optional<UserEntity> user = userRepository.findByUserUuid(userId);
                Optional<ProductEntity> product = productRepository.findByUuid(questionRequestDTO.getProductId());
                if (product.isPresent()){
                    QuestionAreaEntity existQuestionArea = questionAreaRepository.findByProductEntityAndUserEntity(product.get(), user.get());
                    if (existQuestionArea != null){
                        QuestionEntity questionEntity = createQuestionEntity(questionRequestDTO, existQuestionArea);
                        QuestionEntity questionResponse = questionRepository.save(questionEntity);
                        if (questionResponse != null){
                            return new ResponseEntity<>("Question sent",HttpStatus.OK);
                        }else {
                            return new ResponseEntity<>("Question sent failed",HttpStatus.BAD_REQUEST);
                        }
                    }else {
                        QuestionAreaEntity questionAreaEntity = new QuestionAreaEntity();
                        questionAreaEntity.setUserEntity(user.get());
                        questionAreaEntity.setProductEntity(product.get());

                        QuestionAreaEntity questionAreaResponse = questionAreaRepository.save(questionAreaEntity);
                        if (questionAreaResponse != null){
                            QuestionEntity questionEntity = createQuestionEntity(questionRequestDTO, questionAreaResponse);
                            QuestionEntity questionResponse = questionRepository.save(questionEntity);
                            if (questionResponse != null){
                                return new ResponseEntity<>("Question sent",HttpStatus.OK);
                            }else {
                                return new ResponseEntity<>("Question send failed",HttpStatus.BAD_REQUEST);
                            }
                        }else {
                            return new ResponseEntity<>("Question area not created",HttpStatus.BAD_REQUEST);
                        }
                    }
                }else {
                    return new ResponseEntity<>("Product not found",HttpStatus.BAD_REQUEST);
                }
            }else {
                return new ResponseEntity<>("Question not found", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity getQuestion(String productId) {
        try{
            Optional<ProductEntity> product = productRepository.findByUuid(productId);
            List<QuestionResponseDTO> questionResponseList = new ArrayList<>();
            if (product.isPresent()){
                List<QuestionAreaEntity> questionAreaList = questionAreaRepository.findByProductEntity(product.get());
                if (!questionAreaList.isEmpty()){
                    for (QuestionAreaEntity questionAreaEntity :
                            questionAreaList) {
                        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
                        questionResponseDTO.setUserId(questionAreaEntity.getUserEntity().getUserUuid());
                        questionResponseDTO.setUserName(questionAreaEntity.getUserEntity().getFirstName());

                        List<QuestionDTO> questionList = new ArrayList<>();

                        List<QuestionEntity> questionEntityList = questionAreaEntity.getQuestionEntityList();
                        for (QuestionEntity questionEntity :
                                questionEntityList) {
                            if (questionEntity.isApprove()){
                                QuestionDTO questionDTO = new QuestionDTO();
                                questionDTO.setQuestionId(questionEntity.getQuestionId());
                                questionDTO.setQuestion(questionEntity.getQuestion());
                                questionDTO.setQuestionDate(questionEntity.getQuestionDate());
                                questionDTO.setQuestionTime(questionEntity.getQuestionTime());

                                List<AnswerDTO> answerList = new ArrayList<>();

                                List<AnswerEntity> answerEntityList = questionEntity.getAnswerEntityList();
                                for (AnswerEntity answerEntity :
                                        answerEntityList) {
                                    answerList.add(new AnswerDTO(
                                            answerEntity.getAnswerId(),
                                            answerEntity.getAnswer(),
                                            answerEntity.getAnswerDate(),
                                            answerEntity.getAnswerTime()
                                    ));
                                }
                                questionDTO.setAnswerList(answerList);
                                questionList.add(questionDTO);
                            }
                        }
                        if (!questionList.isEmpty()){
                            questionResponseDTO.setQuestionList(questionList);
                            questionResponseList.add(questionResponseDTO);
                        }
                    }
                    return new ResponseEntity(questionResponseList,HttpStatus.OK);
                }else {
                    return new ResponseEntity("Questions not found",HttpStatus.NO_CONTENT);
                }
            }else {
                return new ResponseEntity("Product not found",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    private QuestionEntity createQuestionEntity(QuestionRequestDTO questionRequestDTO,QuestionAreaEntity questionAreaEntity){
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestion(questionRequestDTO.getQuestion());
        questionEntity.setQuestionDate(DateConverter.localDateToSql(LocalDate.now()));
        questionEntity.setQuestionTime(DateConverter.localTimeToSql(LocalTime.now()));
        questionEntity.setApprove(false);
        questionEntity.setQuestionAreaEntity(questionAreaEntity);

        return questionEntity;
    }
}
