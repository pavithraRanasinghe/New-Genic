package lk.robot.newgenic.dto;

import java.sql.Date;
import java.sql.Time;

public class AnswerDTO {

    private long answerId;
    private String answer;
    private Date answerDate;
    private Time answerTime;

    public AnswerDTO() {
    }

    public AnswerDTO(long answerId,
                     String answer,
                     Date answerDate,
                     Time answerTime) {
        this.answerId = answerId;
        this.answer = answer;
        this.answerDate = answerDate;
        this.answerTime = answerTime;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public Time getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Time answerTime) {
        this.answerTime = answerTime;
    }

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "answerId=" + answerId +
                ", answer='" + answer + '\'' +
                ", answerDate=" + answerDate +
                ", answerTime=" + answerTime +
                '}';
    }
}
