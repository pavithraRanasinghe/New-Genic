package lk.robot.newgenic.dto.response;

import lk.robot.newgenic.dto.AnswerDTO;
import lk.robot.newgenic.dto.QuestionDTO;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class QuestionResponseDTO {

    private String userId;
    private String userName;
    private List<QuestionDTO> questionList;

    public QuestionResponseDTO() {
    }

    public QuestionResponseDTO(String userId,
                               String userName,
                               List<QuestionDTO> questionList) {
        this.userId = userId;
        this.userName = userName;
        this.questionList = questionList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<QuestionDTO> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionDTO> questionList) {
        this.questionList = questionList;
    }

    @Override
    public String toString() {
        return "QuestionResponseDTO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", questionList=" + questionList +
                '}';
    }
}
