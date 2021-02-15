package lk.robot.newgenic.dto.request;

public class QuestionRequestDTO {

    private String productId;
    private String question;

    public QuestionRequestDTO() {
    }

    public QuestionRequestDTO(String productId,
                              String question) {
        this.productId = productId;
        this.question = question;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "QuestionRequestDTO{" +
                "productId=" + productId +
                ", question='" + question + '\'' +
                '}';
    }
}
