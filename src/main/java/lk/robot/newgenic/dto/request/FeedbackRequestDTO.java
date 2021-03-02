package lk.robot.newgenic.dto.request;

public class FeedbackRequestDTO {
    private int rate;
    private String message;
    private String productId;

    public FeedbackRequestDTO() {
    }

    public FeedbackRequestDTO(int rate,
                              String message,
                              String productId) {
        this.rate = rate;
        this.message = message;
        this.productId = productId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "FeedbackRequestDTO{" +
                "rate=" + rate +
                ", message='" + message + '\'' +
                ", productId=" + productId +
                '}';
    }
}
