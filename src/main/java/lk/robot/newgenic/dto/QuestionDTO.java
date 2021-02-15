package lk.robot.newgenic.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class QuestionDTO {

    private long questionId;
    private String question;
    private Date questionDate;
    private Time questionTime;
    private List<AnswerDTO> answerList;

    public QuestionDTO() {
    }

    public QuestionDTO(long questionId,
                       String question,
                       Date questionDate,
                       Time questionTime,
                       List<AnswerDTO> answerList) {
        this.questionId = questionId;
        this.question = question;
        this.questionDate = questionDate;
        this.questionTime = questionTime;
        this.answerList = answerList;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(Date questionDate) {
        this.questionDate = questionDate;
    }

    public Time getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Time questionTime) {
        this.questionTime = questionTime;
    }

    public List<AnswerDTO> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerDTO> answerList) {
        this.answerList = answerList;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "questionId=" + questionId +
                ", question='" + question + '\'' +
                ", questionDate=" + questionDate +
                ", questionTime=" + questionTime +
                ", answerList=" + answerList +
                '}';
    }
}
