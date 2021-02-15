package lk.robot.newgenic.service.impl;

import lk.robot.newgenic.dto.request.QuestionRequestDTO;
import lk.robot.newgenic.entity.ProductEntity;
import lk.robot.newgenic.entity.QuestionAreaEntity;
import lk.robot.newgenic.entity.QuestionEntity;
import lk.robot.newgenic.entity.UserEntity;
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
