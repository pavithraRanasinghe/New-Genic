package lk.robot.newgenic.dto.response;

import java.util.Date;

public class ProductFeedbackResponseDTO {

    private long productFeedbackId;
    private int rate;
    private String message;
    private Date addedDate;
    private UserFeedbackDTO userFeedbackDTO;

    public ProductFeedbackResponseDTO() {
    }

    public ProductFeedbackResponseDTO(long productFeedbackId, int rate, String message, Date addedDate, UserFeedbackDTO userFeedbackDTO) {
        this.productFeedbackId = productFeedbackId;
        this.rate = rate;
        this.message = message;
        this.addedDate = addedDate;
        this.userFeedbackDTO = userFeedbackDTO;
    }

    public long getProductFeedbackId() {
        return productFeedbackId;
    }

    public void setProductFeedbackId(long productFeedbackId) {
        this.productFeedbackId = productFeedbackId;
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

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public UserFeedbackDTO getUserFeedbackDTO() {
        return userFeedbackDTO;
    }

    public void setUserFeedbackDTO(UserFeedbackDTO userFeedbackDTO) {
        this.userFeedbackDTO = userFeedbackDTO;
    }

    @Override
    public String toString() {
        return "ProductFeedbackResponseDTO{" +
                "productFeedbackId=" + productFeedbackId +
                ", rate=" + rate +
                ", message='" + message + '\'' +
                ", addedDate=" + addedDate +
                ", userFeedbackDTO=" + userFeedbackDTO +
                '}';
    }
}
