package lk.robot.newgenic.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "question")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private long questionId;
    private String question;
    @Column(name = "question_date")
    private Date questionDate;
    @Column(name = "question_time")
    private Time questionTime;
    @Column(name = "is_approve")
    private boolean isApprove;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_question_area")
    private QuestionAreaEntity questionAreaEntity;

    public QuestionEntity() {
    }

    public QuestionEntity(long questionId,
                          String question,
                          Date questionDate,
                          Time questionTime,
                          boolean isApprove,
                          QuestionAreaEntity questionAreaEntity) {
        this.questionId = questionId;
        this.question = question;
        this.questionDate = questionDate;
        this.questionTime = questionTime;
        this.isApprove = isApprove;
        this.questionAreaEntity = questionAreaEntity;
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

    public boolean isApprove() {
        return isApprove;
    }

    public void setApprove(boolean approve) {
        isApprove = approve;
    }

    public QuestionAreaEntity getQuestionAreaEntity() {
        return questionAreaEntity;
    }

    public void setQuestionAreaEntity(QuestionAreaEntity questionAreaEntity) {
        this.questionAreaEntity = questionAreaEntity;
    }

    @Override
    public String toString() {
        return "QuestionEntity{" +
                "questionId=" + questionId +
                ", question='" + question + '\'' +
                ", questionDate=" + questionDate +
                ", questionTime=" + questionTime +
                ", isApprove=" + isApprove +
                ", questionAreaEntity=" + questionAreaEntity +
                '}';
    }
}
