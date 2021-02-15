package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "answer")
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private long answerId;
    private String answer;
    @Column(name = "answer_date")
    private Date answerDate;
    @Column(name = "answer_time")
    private Time answerTime;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_question_id")
    private QuestionEntity questionEntity;

    public AnswerEntity() {
    }

    public AnswerEntity(long answerId,
                        String answer,
                        Date answerDate,
                        Time answerTime,
                        QuestionEntity questionEntity) {
        this.answerId = answerId;
        this.answer = answer;
        this.answerDate = answerDate;
        this.answerTime = answerTime;
        this.questionEntity = questionEntity;
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

    public QuestionEntity getQuestionEntity() {
        return questionEntity;
    }

    public void setQuestionEntity(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
    }

    @Override
    public String toString() {
        return "AnswerEntity{" +
                "answerId=" + answerId +
                ", answer='" + answer + '\'' +
                ", answerDate=" + answerDate +
                ", answerTime=" + answerTime +
                ", questionEntity=" + questionEntity +
                '}';
    }
}
